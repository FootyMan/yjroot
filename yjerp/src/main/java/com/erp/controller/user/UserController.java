package com.erp.controller.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.erp.model.PositionModel;
import com.erp.model.ProvinceModel;
import com.erp.model.UserImageModel;
import com.erp.model.UserModel;
import com.erp.utils.PositionUtils;
import com.google.gson.Gson;
import com.service.api.impl.InvitationCodeServiceImpl;
import com.service.api.impl.ProvinceServiceImpl;
import com.service.api.impl.UserBrowseExtServiceImpl;
import com.service.api.impl.UserDatumServiceImpl;
import com.service.api.impl.UserImgServiceImpl;
import com.service.api.impl.UserInviteServiceImpl;
import com.service.api.impl.UserPositionServiceImpl;
import com.service.api.impl.UserServiceImpl;
import com.service.bean.HomeUser;
import com.service.bean.InvitationCode;
import com.service.bean.Province;
import com.service.bean.User;
import com.service.bean.UserBrowseExt;
import com.service.bean.UserDatum;
import com.service.bean.UserImg;
import com.service.bean.UserInvite;
import com.service.bean.UserPosition;
import com.service.easemob.EaseMobBusiness;
import com.service.easemob.NeteaseBusiness;
import com.service.easemob.NeteaseModel;
import com.service.enums.DeviceType;
import com.service.enums.UserLevel;
import com.service.erp.impl.HomeUserServiceImplERP;
import com.service.erp.impl.UserImgServiceImplERP;
import com.service.erp.impl.UserServiceImplERP;
import com.service.utils.MandDaoClient;
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
	@Autowired
	private UserImgServiceImplERP userImgServiceImplERP;
	@Autowired
	private HomeUserServiceImplERP homeUserServiceImplERP;
	@Autowired
	private UserBrowseExtServiceImpl userBrowseExtServiceImpl;
	@Autowired
	private UserPositionServiceImpl userPositionServiceImpl;
	@Autowired
	private InvitationCodeServiceImpl invitationCodeServiceImpl;
	@Autowired
	private UserInviteServiceImpl userInviteServiceImpl;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(UserModel userModel, Model model) {// Employee

		User userParameter = new User();
		Pagination pagination = userModel.getPage();
		if (pagination == null) {
			pagination = new Pagination();
		}
		userParameter.setPage(pagination);
		Pagination.threadLocal.set(pagination);

		if (!StringUtils.isEmpty(userModel.getUserNo())) {
			userParameter.setUserNo(userModel.getUserNo());
		} else if (!StringUtils.isEmpty(userModel.getPhone())) {
			userParameter.setPhone(userModel.getPhone());
		} else if (!StringUtils.isEmpty(userModel.getUserLevel())) {
			userParameter.setUserLevel(Integer.parseInt(userModel.getUserLevel()));
		}
		// 查询首页
		List<HomeUser> homeData = homeUserServiceImplERP.selectHomeUserList();
		List<User> empReslist = userServiceImplERP.ErpUserListByPage(userParameter);
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
			m.setIsHomeUser(SetIsHomeUser(homeData, x.getUserId()));
			m.setUserNo(x.getUserNo());
			m.setUserLevelId(x.getUserLevel());
			listUser.add(m);
		}
		model.addAttribute("listUser", listUser);
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/user/list");
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(UserModel userModel, Model model) {// Employee
		// employee,

		// 初始化用户信息
		if (userModel.getUserId() > 0) {
			List<User> entity_Users = userServiceImplERP.selectDetalsERP(userModel.getUserId());
			if (entity_Users != null && entity_Users.size() > 0) {
				User enUser = entity_Users.get(0);
				userModel.setHeadImage(SystemConfig.ImgurlPrefix + enUser.getHeadImage());
				if (enUser.getHeadImage().indexOf("http") != -1) {
					userModel.setHeadImage(enUser.getHeadImage());
				}
				userModel.setPhone(enUser.getPhone());
				userModel.setDeviceType(enUser.getDeviceType());
				userModel.setNickName(enUser.getNickName());
				userModel.setInviteCode(enUser.getInviteCode());
				// 查询用户资料
				UserDatum enDatum = enUser.getDatum();
				userModel.setAge(enDatum.getAge());
				userModel.setSex(enDatum.getGender());
				userModel.setCityId(enDatum.getCityId());
				userModel.setWeight(enDatum.getWeight());
				userModel.setHeight(enDatum.getHeight());
				userModel.setShape(enDatum.getShape());
				userModel.setSexuat(enDatum.getSexuat());
				userModel.setSign(enDatum.getSign());
				List<UserImageModel> imgS = new ArrayList<UserImageModel>();
				List<UserImg> entity_Imgs = userImgServiceImpl.selectImgtByUserId(userModel.getUserId());
				for (UserImg userImg : entity_Imgs) {
					UserImageModel imgModel = new UserImageModel();
					imgModel.setImageId(userImg.getImgId());
					imgModel.setImgUrl(SystemConfig.ImgurlPrefix + userImg.getImagePath());
					if (userImg.getImagePath().indexOf("http") != -1) {
						imgModel.setImgUrl(userImg.getImagePath());
					}
					imgS.add(imgModel);
				}
				userModel.setImgList(imgS);
			}

		}
		// 初始化城市
		List<ProvinceModel> listPro = new ArrayList<ProvinceModel>();
		List<Province> proEntiys = provinceServiceImpl.selectProvinces();
		for (Province pro : proEntiys) {
			ProvinceModel proModel = new ProvinceModel();
			proModel.setProvinceId(pro.getProvinceId());
			proModel.setName(pro.getName());
			listPro.add(proModel);
		}
		model.addAttribute("obj", userModel);
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

		if (userModel.getUserId() > 0) {
			// 更新图片
			List<UserImageModel> imgList = userModel.getImgList();
			// 更新
			User user = SetUserEntity(userModel, userModel.getUserId());
			userServiceImpl.updateUser(user);
			// 更新基本资料
			UserDatum datum = SetUserDatum(userModel, userModel.getUserId());
			userDatumServiceImpl.updateDatum(datum);

			// 更新之前图片无效 从新添加
			userImgServiceImplERP.updateUserImgStatus(userModel.getUserId());
			AddUserImage(imgList, userModel.getUserId());

		} else {
			InsertUser(userModel);
		}
		return new ModelAndView("redirect:/user/list");
	}

	/**
	 * 添加
	 * 
	 * @param userModel
	 * @return
	 */
	private int InsertUser(UserModel userModel) {
		List<UserImageModel> imgList = userModel.getImgList();
		User entiyUser = new User();
		entiyUser = SetUserEntity(userModel, 0);
		int insertResult = userServiceImplERP.InsertUserErp(entiyUser);
		if (insertResult > 0) {
			int userId = entiyUser.getUserId();
			// 添加基本资料
			UserDatum datum = SetUserDatum(userModel, userId);
			int datumId = userDatumServiceImpl.insertDatum(datum);
			if (datumId > 0) {
				AddUserImage(imgList, userId);
				// 注册环信
				String easemobId = userId + SystemConfig.EaseSuffixId;
				NeteaseModel netease = NeteaseBusiness.CreateaccId(easemobId, userId);
				// String result = EaseMobBusiness.AccountCreate(easemobId);
				// Map map = (Map) JSON.parse(result);
				// if (map != null && !map.containsKey("error")) {
				if (netease != null) {
					// 更新用户
					User upUser = new User();
					upUser.setUserId(userId);
					upUser.setEasemobId(easemobId);
					upUser.setIsEasemob(1);
					userServiceImpl.updateUser(upUser);

				}
				// 添加浏览记录表
				UserBrowseExt ext = new UserBrowseExt();
				ext.setUserId(userId);
				ext.setBrowseNumber(0);
				userBrowseExtServiceImpl.insertBrowseExt(ext);
				// 添加经纬度
				PositionModel resultStr = PositionUtils.GetpositionByAddress(userModel.getCityName());
				UserPosition position = new UserPosition();
				position.setIsPosition(1);
				position.setUserId(userId);
				position.setLongitude(resultStr.getLon());
				position.setLatitude(resultStr.getLat());

				userPositionServiceImpl.insertPosition(position);
				User codeData = userServiceImpl.selectUserByInviteCode(userModel.getRegisterCode());
				if (codeData != null) {
					// 添加邀请
					UserInvite invite = new UserInvite();
					invite.setInviteUserId(codeData.getUserId());
					invite.setInviteCode(codeData.getInviteCode());
					invite.setRegisterUserId(userId);
					userInviteServiceImpl.insertInvite(invite);
				}

			}
		}
		return insertResult;
	}

	/**
	 * 更新和添加设置对象
	 * 
	 * @param userModel
	 * @param userid
	 * @return
	 */
	public User SetUserEntity(UserModel userModel, int userid) {
		User entiyUser = new User();
		if (userid > 0) {
			// 更新
			entiyUser.setUserId(userid);
			String headImg = userModel.getHeadImage().replace(SystemConfig.ImgurlPrefix, "");
			entiyUser.setHeadImage(headImg);
			entiyUser.setNickName(userModel.getNickName());
		} else {

			// 获取邀请码
			InvitationCode inCode = invitationCodeServiceImpl.selectCodeByvalid();
			entiyUser.setInviteCode(inCode.getCode());// inCode.getCode()
			// 添加
			entiyUser.setPhone(userModel.getPhone());
			entiyUser.setDeviceType(userModel.getDeviceType());
			entiyUser.setDeviceToken("XXXX-XXXX-XXXX-XXXX");
			String headImg = userModel.getHeadImage().replace(SystemConfig.ImgurlPrefix, "");
			entiyUser.setHeadImage(headImg);
			entiyUser.setNickName(userModel.getNickName());

			entiyUser.setPassWord(Md5Util.stringByMD5("000000"));
		}
		return entiyUser;
	}

	/**
	 * 基本资料
	 * 
	 * @param userModel
	 * @param userId
	 * @return
	 */
	public UserDatum SetUserDatum(UserModel userModel, int userId) {
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
		return datum;
	}

	/**
	 * 添加图片
	 * 
	 * @param imgList
	 * @param userId
	 * @return
	 */
	public int AddUserImage(List<UserImageModel> imgList, int userId) {
		int result = 0;
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
			result = userImgServiceImpl.insertUserImg(entiyImgs);
		}
		return result;
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
			//
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

	@RequestMapping(value = "/setHome", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int SetHomeUser(int userId, int type, Model model) {

		int result = -1;
		HomeUser homeResult = homeUserServiceImplERP.selectHomeUserByUserId(userId);
		if (type == 1)// 添加
		{
			if (homeResult != null) {

				return result;// 已存在
			}
			// 添加
			HomeUser homeEntity = new HomeUser();
			homeEntity.setUserId(userId);
			homeEntity.setHomeType(1);
			homeEntity.setSortColumn(1);
			int insertResult = homeUserServiceImplERP.insertHomeUser(homeEntity);
			if (insertResult > 0) {
				return insertResult;
			} else {
				return 0;
			}
		}

		else if (type == 2)// 删除
		{
			result = homeUserServiceImplERP.deleteHomeUser(homeResult.getHomeId());
		}
		return result;
	}

	/**
	 * 首页是否存在
	 * 
	 * @param data
	 * @param userId
	 * @return
	 */
	public int SetIsHomeUser(List<HomeUser> data, int userId) {
		if (data != null) {

			// Predicate<HomeUser> predicate = n -> n.getUserId() == userId;
			Optional<HomeUser> home = data.stream().filter(p -> p.getUserId() == userId).findFirst();
			if (home.isPresent()) {
				return 1;
			}
		}
		return 0;
	}

	@RequestMapping(value = "/sendMsg", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int SendEaseMessage(String message) throws InterruptedException {
		List<String> listStr = new ArrayList<String>();
		Gson gson = new Gson();
		int j = 1;
		List<String> easeData = userServiceImplERP.selectEaseIdAll();
		if (easeData.size() >= 90) {

			for (int i = 0; i < easeData.size(); i++) {
				if (j >= 90) {
					// 开始发送
					// String strUser = String.join(",", listStr);
					// 发送消息

					// listStr.add("1");listStr.add("2");listStr.add("3");listStr.add("4");listStr.add("5");
					// listStr.add("6");
					// listStr.add("7");listStr.add("8");
					String strUser = gson.toJson(listStr);
					EaseMobBusiness.SendMessage(strUser, message);
					listStr.clear();
					j = 0;
					Thread.sleep(3000);// 线程休眠3秒

				}
				if (!StringUtils.isEmpty(easeData.get(i))) {
					listStr.add(easeData.get(i));
				}

				j++;
			}
		} else {

			String strUser = gson.toJson(easeData);
			// 发送消息
			EaseMobBusiness.SendMessage(strUser, message);
		}

		// EaseMobBusiness.SendMessage(strUser, msg)
		return 1;
	}

	@RequestMapping(value = "/easeMessage", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView easeMessage(Model model) {// Employee
		return new ModelAndView("/user/easeMessage");
	}

	@RequestMapping(value = "/messageIndex", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView MessageIndex(Model model) {// Employee
		return new ModelAndView("/user/message");
	}
	@RequestMapping(value = "/setLevel", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int SetUserLevel(int userId, int type, Model model) {

		int result = -1;
		User user = new User();
		user.setUserId(userId);
		// 取消会员
		if (type == 1) {
			user.setUserLevel(1);
			result = userServiceImpl.updateUser(user);
		}
		// 设为会员
		else {
			user.setUserLevel(4);
			result = userServiceImpl.updateUser(user);
		}
		return result;
	}

	@RequestMapping(value = "/sendMessage", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int SendMessage(String phone, int type, String msg, Model model) {
		MandDaoClient client;
		String result_mt = "";
		try {
			client = new MandDaoClient();
			String content = URLEncoder.encode(MandDaoClient.sign + msg, "utf8");
			// 单个会员
			if (type == 1 && !StringUtils.isEmpty(phone)) {
				result_mt = client.mdSmsSend_u(phone, content, "", "", "");
			}
			// 所有
			else {

				Predicate<User> contain = n -> n.getIsImport() == 0;
				List<User> user_data = userServiceImplERP.ImportUser();
				user_data.stream().filter(contain).forEach(P -> {

					client.mdSmsSend_u(P.getPhone(), content, "", "", "");
				});
			}
			if (result_mt.startsWith("-") || result_mt.equals(""))// 发送短信，如果是以负号开头就是发送失败。
			{
				System.out.print("发送失败！返回值为：" + result_mt + "请查看webservice返回值对照表");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}
}
