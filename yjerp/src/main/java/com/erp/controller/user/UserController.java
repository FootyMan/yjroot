package com.erp.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.erp.model.ProvinceModel;
import com.erp.model.UserImageModel;
import com.erp.model.UserModel;
import com.service.api.impl.ProvinceServiceImpl;
import com.service.api.impl.UserDatumServiceImpl;
import com.service.api.impl.UserImgServiceImpl;
import com.service.api.impl.UserServiceImpl;
import com.service.bean.Employee;
import com.service.bean.Province;
import com.service.bean.User;
import com.service.bean.UserDatum;
import com.service.bean.UserImg;
import com.service.easemob.EaseMobBusiness;
import com.service.enums.DeviceType;
import com.service.enums.UserLevel;
import com.service.erp.impl.UserServiceImplERP;
import com.service.utils.Md5Util;
import com.service.utils.Pagination;
import com.service.utils.StringUtils;
import com.service.utils.SystemConfig;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserServiceImplERP userServiceImplERP;
	@Autowired
	private ProvinceServiceImpl provinceServiceImpl;
	@Autowired
	private UserDatumServiceImpl userDatumServiceImpl;
	@Autowired
	private UserImgServiceImpl userImgServiceImpl;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(UserModel userModel, Model model) {// Employee
																// employee,
		Pagination pagination = userModel.getPage();
		if (pagination == null) {
			pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		User user = new User();
		List<User> empReslist = userServiceImplERP.GetUserList(user);
		List<UserModel> listUser = new ArrayList<UserModel>();
		for (User x : empReslist) {
			UserModel m = new UserModel();
			UserDatum datm = x.getDatum();
			m.setUserId(x.getUserId());
			m.setUserNo(String.valueOf(x.getUserId()));
			m.setPhone(x.getPhone());
			m.setDeviceTypeName(DeviceType.getDeviceTypeByCode(x.getDeviceType()).getDesc());
			m.setNickName(x.getNickName());
			m.setRegisterTime(x.getRegisterTime());
			m.setUserLevel(UserLevel.getOrderStateByCode(x.getUserLevel()).getDesc());
			m.setIsDisable(x.getIsDisable() == 1 ? "启用" : "禁用");
			m.setInviteCode(x.getInviteCode());
			m.setAge(datm.getAge());
			m.setSexName(datm.getGender() == 1 ? "男" : "女");
			m.setCityName(provinceServiceImpl.SelectProvincesById(datm.getCityId()).getName());
			m.setSexuat(datm.getSexuat());
			listUser.add(m);
		}
		model.addAttribute("listUser", listUser);
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/user/list");
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(UserModel userModel, Model model) {// Employee
		// employee,

		// 初始化城市
		List<ProvinceModel> listPro = new ArrayList<ProvinceModel>();
		List<Province> proEntiys = provinceServiceImpl.selectProvinces();
		for (Province pro : proEntiys) {
			ProvinceModel proModel = new ProvinceModel();
			proModel.setProvinceId(pro.getProvinceId());
			proModel.setName(pro.getName());
			listPro.add(proModel);
		}
		model.addAttribute("listPro", listPro);
		return new ModelAndView("/user/add");
	}

	/**
	 * 添加用户
	 * 
	 * @param userModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adddo(UserModel userModel, Model model, HttpServletRequest request) {// Employee
		// employee,
		List<UserImageModel> imgList = userModel.getImgList();
		User entiyUser = new User();
		entiyUser.setPhone(userModel.getPhone());
		entiyUser.setDeviceType(userModel.getDeviceType());
		entiyUser.setDeviceToken("");
		String headImg = userModel.getHeadImage().replace(SystemConfig.ImgurlPrefix, "");
		entiyUser.setHeadImage(headImg);
		entiyUser.setNickName(userModel.getNickName());
		entiyUser.setInviteCode(userModel.getInviteCode());
		entiyUser.setPassWord(Md5Util.stringByMD5("000000"));
		int userId = userServiceImplERP.InsertUserErp(entiyUser);
		if (userId > 0) {
			// 添加基本资料
			UserDatum datum = new UserDatum();
			datum.setUserId(userId);
			datum.setAge(userModel.getAge());
			datum.setGender(userModel.getSex());
			datum.setCityId(userModel.getCityId());
			datum.setWeight(userModel.getWeight());
			datum.setHeight(userModel.getHeight());
			datum.setShape(userModel.getShape());
			datum.setSexuat(userModel.getSexuat());
			datum.setSign(userModel.getSign());
			int datumId = userDatumServiceImpl.insertDatum(datum);
			if (datumId > 0) {
				// 添加图片
				if (imgList != null && imgList.size() > 0) {
					List<UserImg> entiyImgs = new ArrayList<UserImg>();
					for (int i = 0; i < imgList.size(); i++) {
						UserImageModel m = imgList.get(i);
						if (!StringUtils.isEmpty(m.getImgUrl())) {
							UserImg img = new UserImg();
							img.setUserId(userId);
							img.setImageType(0);
							String paht = m.getImgUrl().replace(SystemConfig.ImgurlPrefix, "");
							img.setImagePath(paht);
							img.setImageSort(i);
							img.setImgStatus(1);
							entiyImgs.add(img);
						}

					}
					userImgServiceImpl.insertUserImg(entiyImgs);
				}
				// 注册环信
				String easemobId = userId + SystemConfig.EnvIdentity;
				String result = EaseMobBusiness.AccountCreate(easemobId);
				Map map = (Map) JSON.parse(result);
				if (map != null && !map.containsKey("error")) {
					// 更新用户
					User upUser = new User();
					upUser.setUserId(userId);
					upUser.setEasemobId(easemobId);
					upUser.setIsEasemob(1);
					userServiceImpl.updateUser(upUser);

				}
			}
		}
		return new ModelAndView("/user/list");
	}

	/**
	 * 首页用户列表
	 * 
	 * @param userModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Home(UserModel userModel, Model model) {// Employee
																// employee,
		Pagination pagination = userModel.getPage();
		if (pagination == null) {
			pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		User user = new User();
		List<User> empReslist = userServiceImplERP.GetHomeUserList(user);
		List<UserModel> listUser = new ArrayList<UserModel>();
		for (User x : empReslist) {
			UserModel m = new UserModel();
			UserDatum datm = x.getDatum();
			m.setUserId(x.getUserId());
			m.setUserNo(String.valueOf(x.getUserId()));
			m.setPhone(x.getPhone());
			// m.setDeviceType(DeviceType.getDeviceTypeByCode(x.getDeviceType()).getDesc());
			m.setNickName(x.getNickName());
			m.setRegisterTime(x.getRegisterTime());
			m.setUserLevel(UserLevel.getOrderStateByCode(x.getUserLevel()).getDesc());
			m.setIsDisable(x.getIsDisable() == 1 ? "启用" : "禁用");
			m.setInviteCode(x.getInviteCode());
			m.setAge(datm.getAge());
			// m.setSex(datm.getGender() == 1 ? "男" : "女");
			m.setCityName(provinceServiceImpl.SelectProvincesById(datm.getCityId()).getName());
			m.setSexuat(datm.getSexuat());
			listUser.add(m);
		}

		model.addAttribute("listUser", listUser);
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/user/home");
	}
}
