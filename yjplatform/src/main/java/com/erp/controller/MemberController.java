package com.erp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.erp.aspect.Log;
import com.erp.dao.MemberDatumDao;
import com.erp.impl.HomeMemberImpl;
import com.erp.impl.MemberImgImpl;
import com.erp.impl.MemberRechargeImpl;
import com.erp.impl.ProvincImpl;
import com.erp.model.ProvinceModel;
import com.erp.model.UserImageModel;
import com.erp.model.UserModel;
import com.erp.service.MemberService;
import com.erp.utils.PageUtils;
import com.erp.utils.R;
import com.service.bean.HomeUser;
import com.service.bean.Province;
import com.service.bean.User;
import com.service.bean.UserDatum;
import com.service.bean.UserImg;
import com.service.bean.UserRecharge;
import com.service.enums.DeviceType;
import com.service.enums.UserLevel;
import com.service.utils.StringUtils;
import com.service.utils.SystemConfig;

@RequestMapping("/erp/member")
@Controller
public class MemberController extends BaseController {
	private String prefix = "erp/member";
	@Autowired
	MemberService memberService;//会员
	@Autowired
	HomeMemberImpl homeMemberImpl;//首页
	@Autowired
	private ProvincImpl provincImpl;//城市
	@Autowired
	private MemberImgImpl memberImgImpl;//图片
	@Autowired
	private MemberDatumDao memberDatumDao;//资料
	@Autowired
	private MemberRechargeImpl memberRechargeImpl;//充值
//	@Autowired
//	PropertiesSystemConfig systemConfig;

	@RequiresPermissions("erp:member:list")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/list";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		List<User> empReslist = memberService.GetUserList(params);
		// 查询首页
		List<HomeUser> homeData = homeMemberImpl.selectHomeUserList();
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
			m.setUserLevelId(x.getUserLevel());
			m.setIsDisable(x.getIsDisable());
			m.setInviteCode(x.getInviteCode());
			m.setAge(datm.getAge());
			m.setSex(datm.getGender());
			m.setSexuat(datm.getSexuat());
			m.setIsHomeUser(SetIsHomeUser(homeData, x.getUserId()));
			m.setUserNo(x.getUserNo());
			m.setUserLevelId(x.getUserLevel());
			m.setHeadImage(SystemConfig.ImgurlPrefix+ x.getHeadImage());
			if (x.getHeadImage().indexOf("http") != -1) {
				m.setHeadImage(x.getHeadImage());
			}
			listUser.add(m);
		}
		int total = memberService.GetUserListCount(params);
		PageUtils pageUtil = new PageUtils(listUser, total);
		return pageUtil;
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

	/**
	 * 编辑初始化
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("erp:member:edit")
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") int id) {

		UserModel userModel=GetUserModel(id);
		// 初始化城市
		List<ProvinceModel> listPro = new ArrayList<ProvinceModel>();
		List<Province> proEntiys = provincImpl.selectProvinces();
		for (Province pro : proEntiys) {
			ProvinceModel proModel = new ProvinceModel();
			proModel.setProvinceId(pro.getProvinceId());
			proModel.setName(pro.getName());
			listPro.add(proModel);
		}
		model.addAttribute("obj", userModel);
		model.addAttribute("listPro", listPro);
		return prefix + "/add";
	}

	/**
	 * 添加用户
	 * 
	 * @param userModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("erp:member:edit")
	@Log("保存用户用户")
	@PostMapping("/save")
	@ResponseBody
	R save(UserModel userModel) {
		if (userModel.getUserId() > 0) {
			// 更新图片
			List<UserImageModel> imgList = userModel.getImgList();
			// 更新
			User user = new User();
			user.setUserId(userModel.getUserId());
			String headImg = userModel.getHeadImage().replace(SystemConfig.ImgurlPrefix, "");
			user.setHeadImage(headImg);
			user.setNickName(userModel.getNickName());
			memberService.updateUser(user);
			// 更新基本资料
			UserDatum datum = SetUserDatum(userModel, userModel.getUserId());
			memberDatumDao.updateDatum(datum);

			// 更新之前图片无效 从新添加
			memberImgImpl.updateUserImgStatus(userModel.getUserId());
			int result = AddUserImage(imgList, userModel.getUserId());
			if (result > 0) {
				return R.ok();
			}
		}
		return R.error();
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
			result = memberImgImpl.insertUserImg(entiyImgs);
		}
		return result;
	}

	@RequiresPermissions("erp:member:sethome")
	@Log("设置用户到首页")
	@PostMapping("/sethome")
	@ResponseBody
	R sethome(@RequestParam("ids[]") int[] userIds) {

		int result = 0;
		for (int i = 0; i < userIds.length; i++) {
			int userId = userIds[i];
			HomeUser homeResult = homeMemberImpl.selectHomeUserByUserId(userId);
			if (homeResult != null) {

				return R.error("错误！！选择的用户已存在首页");// 已存在
			}
			// 添加
			HomeUser homeEntity = new HomeUser();
			homeEntity.setUserId(userId);
			homeEntity.setHomeType(1);
			homeEntity.setSortColumn(1);
			result = homeMemberImpl.insertHomeUser(homeEntity);
		}
		if (result > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * get方式 使用@GetMapping和@PathVariable
	 * type=1添加会员 2取消会员
	 * @param userId
	 * @param type
	 * @return
	 */
	@RequiresPermissions("erp:member:setmember")
	@Log("设置用户级别")
	@GetMapping("/setmember/{userId}/{type}")
	@ResponseBody
	R setLevel(@PathVariable("userId") int userId,@PathVariable("type") int type) {

		int result = -1;
		User user = new User();
		user.setUserId(userId);
		// 取消会员
		if (type == 2) {
			user.setUserLevel(1);
			result = memberService.updateUser(user);
		}
		// 设为会员
		else {
			user.setUserLevel(4);
			result = memberService.updateUser(user);
		}
		if (result > 0) {
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 1启用 0禁用
	 * @param userId
	 * @param type
	 * @return
	 */
	@RequiresPermissions("erp:member:disable")
	@Log("禁用用户")
	@GetMapping("/disable/{userId}/{type}")
	@ResponseBody
	R disable(@PathVariable("userId") int userId,@PathVariable("type") int type) {

		User user = new User();
		user.setUserId(userId);
		user.setIsDisable(type);
		int result = memberService.setUserIsEnable(user);
		if (result > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@RequiresPermissions("erp:member:info")
	@Log("用户信息")
	@GetMapping("/info/{id}")
	String info(Model model, @PathVariable("id") int id) {

		Map<String, Object> map=new HashMap<>();
		map.put("userId", id);
		System.out.println("开始读取user。。。。。。。。。。。。。。。。。。");
		UserModel userModel=GetUserModel(id);
		System.out.println("结束读取user。。。。。。。。。。。。。。。。。。");
		List<UserRecharge> recharge_data=memberRechargeImpl.selectRechargeByuserId(map);
		model.addAttribute("obj", userModel);
		model.addAttribute("rec", recharge_data);
		return prefix + "/info";
	}
	
	/**
	 * 获取用户信息
	 * @param userId
	 * @return
	 */
	public UserModel GetUserModel(int userId)
	{
		UserModel userModel = new UserModel();
		if (userId > 0) {
			List<User> entity_Users = memberService.selectDetalsERP(userId);
			if (entity_Users != null && entity_Users.size() > 0) {
				User enUser = entity_Users.get(0);
				userModel.setHeadImage(SystemConfig.ImgurlPrefix+ enUser.getHeadImage());
				if (enUser.getHeadImage().indexOf("http") != -1) {
					userModel.setHeadImage(enUser.getHeadImage());
				}
				userModel.setUserId(userId);
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
				userModel.setCityName(provincImpl.SelectProvincesById(enDatum.getCityId()).getName());
				List<UserImageModel> imgS = new ArrayList<UserImageModel>();
				List<UserImg> entity_Imgs = memberImgImpl.selectImgtByUserId(userModel.getUserId());
				int index = 0;
				for (UserImg userImg : entity_Imgs) {
					UserImageModel imgModel = new UserImageModel();
					imgModel.setImageId(userImg.getImgId());
					 imgModel.setImgUrl(SystemConfig.ImgurlPrefix +userImg.getImagePath());
//					imgModel.setImgUrl("http://localhost:806/" + userImg.getImagePath());
					imgModel.setIndex(index);
					imgModel.setUpdateimgtag("img_src_" + index);
					if (userImg.getImagePath().indexOf("http") != -1) {
						imgModel.setImgUrl(userImg.getImagePath());
					}
					++index;
					imgS.add(imgModel);
				}
				userModel.setImgList(imgS);
			}
		}
		return userModel;
	}

}
