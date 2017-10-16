package com.api.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.request.BrowseRequest;
import com.api.request.DetailsRequest;
import com.api.request.LableRequest;
import com.api.request.LableRequestData;
import com.api.request.PhoneMsgRequest;
import com.api.request.RemoveImgRequest;
import com.api.request.UserDatumRequest;
import com.api.request.UserLikeRequest;
import com.api.request.baseRequest;
import com.api.request.userModel;
import com.api.response.BrowseNumberResponse;
import com.api.response.UserImgResponse;
import com.api.response.DetailsLableResponse;
import com.api.response.DetailsResponse;
import com.api.response.HomeResponse;
import com.api.response.InitResponse;
import com.api.response.InitResponseAppData;
import com.api.response.LableResponse;
import com.api.response.LableResponseData;
import com.api.response.MyImageResponse;
import com.api.response.DetailsUserResponse;
import com.api.response.UserLikeResponse;
import com.api.response.UserPwdResponse;
import com.api.response.BaseResponse;
import com.api.utils.PageParameter;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.myErp.impl.InvitationCodeServiceImpl;
import com.myErp.impl.UserBrowseServiceImpl;
import com.myErp.impl.UserDatumServiceImpl;
import com.myErp.impl.UserImgServiceImpl;
import com.myErp.impl.UserInviteServiceImpl;
import com.myErp.impl.UserLableMappingServiceImpl;
import com.myErp.impl.UserServiceImpl;
import com.myErp.impl.UserVerifyCodeServiceImpl;
import com.myErp.impl.UserlikeServiceImpl;
import com.myErp.manager.bean.InvitationCode;
import com.myErp.manager.bean.LabletType;
import com.myErp.manager.bean.RangeParameter;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserBrowse;
import com.myErp.manager.bean.UserDatum;
import com.myErp.manager.bean.UserImg;
import com.myErp.manager.bean.UserInvite;
import com.myErp.manager.bean.UserLableMapping;
import com.myErp.manager.bean.UserVerifyCode;
import com.myErp.manager.bean.Userlike;
import com.myErp.utils.Md5Util;
import com.myErp.utils.StringUtils;
import com.myErp.utils.SystemConfig;

@Service("UserBusiness")
public class UserBusiness {

	private static Logger logger = Logger.getLogger(UserBusiness.class);

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserVerifyCodeServiceImpl userVerifyCodeServiceImpl;
	@Autowired
	private UserDatumServiceImpl userDatumService;
	@Autowired
	private UserLableMappingServiceImpl userLableMappingServiceImpl;
	@Autowired
	private UserImgServiceImpl userImgServiceImpl;
	@Autowired
	private InvitationCodeServiceImpl invitationCodeServiceImpl;
	@Autowired
	private UserInviteServiceImpl userInviteServiceImpl;
	@Autowired
	private UserlikeServiceImpl userlikeServiceImpl;
	@Autowired
	private UserBrowseServiceImpl userBrowseServiceImpl;
	@Autowired
	private InitBusiness initBusiness;
	@Autowired
	private BusinessUtils businessUtils;

	/**
	 * 添加用户验证码
	 */
	public void AddUserVerifyCode(UserVerifyCode verifyCode) {
		userVerifyCodeServiceImpl.insertUserVerifyCode(verifyCode);
	}

	/**
	 * 添加用户标签
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse<LableResponseData> AddUserLable(baseRequest<LableRequestData> request) {
		BaseResponse<LableResponseData> response = new BaseResponse<LableResponseData>();

		int labeTypeId = 0;
		List<LableRequest> lableRequestDatas = request.getbody().getList();
		if (lableRequestDatas != null && lableRequestDatas.size() > 0) {
			// 添加
			List<UserLableMapping> entitys = new ArrayList<UserLableMapping>();
			for (LableRequest item : lableRequestDatas) {
				UserLableMapping mapping = new UserLableMapping();
				mapping.setUserId(request.getUserId());
				mapping.setLableId(item.getLableId());
				mapping.setLableType(item.getLableType());
				labeTypeId = item.getLableType();
				entitys.add(mapping);
			}
			int result = userLableMappingServiceImpl.insertlabletMapping(entitys);
			if (result > 0) {
				LableResponseData lables = new LableResponseData();
				// 添加成功 查询根据用户
				UserLableMapping selectLable = new UserLableMapping();
				selectLable.setUserId(request.getUserId());
				selectLable.setLableType(labeTypeId);
				List<UserLableMapping> userLableData = userLableMappingServiceImpl.selectlabletByUserId(selectLable);
				for (UserLableMapping c : userLableData) {
					LableResponse model = new LableResponse();
					model.setLableId(c.getLableId());
					model.setLableType(c.getLableType());
					model.setLableName(c.getLabletTypes().getLableName());
					lables.getList().add(model);
				}
				response.setData(lables);
			}
		}
		return response;
	}

	/**
	 * 根据用户ID获取他的所有标签
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse<InitResponseAppData> GetUserLableByUserID(baseRequest<?> request) {
		BaseResponse<InitResponseAppData> response = new BaseResponse<InitResponseAppData>();
		UserLableMapping mapping = new UserLableMapping();
		mapping.setUserId(request.getUserId());
		List<UserLableMapping> userLableData = userLableMappingServiceImpl.selectlabletByUserId(mapping);
		if (userLableData != null && userLableData.size() > 0) {
			List<LabletType> types = new ArrayList<LabletType>();
			for (UserLableMapping userLableMapping : userLableData) {
				types.add(userLableMapping.getLabletTypes());
			}
			response.setData(businessUtils.LableEntityToModel(types));
		}
		return response;
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public BaseResponse<InitResponse> userRegister(baseRequest<userModel> user) {
		// 检查手机号是否重复
		// 检查邀请码是否正确
		// 检查短信验证码
		userModel model = user.getbody();
		BaseResponse<InitResponse> response = new BaseResponse<InitResponse>();
		int isExistPhone = userServiceImpl.selectUserByphone(model.getPhone());
		if (isExistPhone > 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("手机号已存在！请登录");
			return response;
		}
		User codeData = userServiceImpl.selectUserByInviteCode(model.getInviteCode());
		if (codeData == null) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("邀请码不正确");
			return response;
		}
		UserVerifyCode userCode = userVerifyCodeServiceImpl.selectCodeByphone(model.getPhone());
		if (userCode == null || !userCode.getVerifyCode().equals(model.getVerifyCode())) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("手机验证码不正确");
			return response;
		}
		// 注册
		User userEntity = new User();
		userEntity.setPhone(model.getPhone());
		userEntity.setDeviceType(user.getDeviceType());
		userEntity.setDeviceToken(user.getDeviceToken());
		userEntity.setUserSource(1);// 默认全部是APP
		userEntity.setPassWord(Md5Util.stringByMD5(model.getPassWord()));
		// 获取邀请码
		InvitationCode inCode = invitationCodeServiceImpl.selectCodeByvalid();
		userEntity.setInviteCode(inCode.getCode());// inCode.getCode()
		int userId = userServiceImpl.insertUser(userEntity);
		if (userId <= 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("注册用户失败");
			return response;
		}
		InitResponse initUser = initBusiness.InitUserData(userEntity.getUserId());
		response.setData(initUser);
		AddUserInvite(userInviteServiceImpl, codeData, userEntity.getUserId());
		return response;

	}

	/**
	 * 修改面
	 * 
	 * @param user
	 * @return
	 */
	public BaseResponse UpdateUserPwd(baseRequest<UserPwdResponse> user) {
		// 检查手机号是否重复
		// 检查邀请码是否正确
		// 检查短信验证码
		UserPwdResponse model = user.getbody();
		BaseResponse response = new BaseResponse();
		int isExistPhone = userServiceImpl.selectUserByphone(model.getPhone());
		if (isExistPhone > 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("手机号已存在！请注册");
			return response;
		}
		UserVerifyCode userCode = userVerifyCodeServiceImpl.selectCodeByphone(model.getPhone());
		if (userCode == null || !userCode.getVerifyCode().equals(model.getVerifyCode())) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("手机验证码不正确");
			return response;
		}
		// 注册
		User userEntity = new User();
		userEntity.setPassWord(Md5Util.stringByMD5(model.getPassWord()));
		int userId = userServiceImpl.updateUser(userEntity);
		if (userId <= 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("修改密码失败");
			return response;
		}
		return response;

	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	public BaseResponse<InitResponse> userLogin(baseRequest<userModel> user) {
		// 检查手机号是否重复
		// 检查邀请码是否正确
		// 检查短信验证码
		userModel model = user.getbody();
		BaseResponse<InitResponse> response = new BaseResponse<InitResponse>();
		// 注册
		User userEntity = new User();
		userEntity.setPhone(model.getPhone());
		userEntity.setPassWord(Md5Util.stringByMD5(model.getPassWord()));
		User resultUser = userServiceImpl.userLogin(userEntity);
		if (resultUser == null) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("用户名或密码错误");
			return response;
		}
		InitResponse initUser = initBusiness.InitUserData(resultUser.getUserId());
		response.setData(initUser);
		return response;

	}

	/**
	 * 开启线程去添加用户邀请记录表
	 * 
	 * @param userInviteServiceImpl
	 * @param invitation
	 *            邀请用户
	 * @param registerId
	 *            注册用户ID
	 */
	public void AddUserInvite(UserInviteServiceImpl userInviteServiceImpl, User invitation, int registerId) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				UserInvite invite = new UserInvite();
				invite.setInviteUserId(invitation.getUserId());
				invite.setInviteCode(invitation.getInviteCode());
				invite.setRegisterUserId(registerId);
				userInviteServiceImpl.insertInvite(invite);
			}
		});
		t.start();
	}

	/**
	 * 添加和修改用户资料
	 * 
	 * @param userServiceImpl
	 * @param user
	 * @return
	 */
	public BaseResponse<InitResponse> AddUserDatum(baseRequest<UserDatumRequest> request) {
		BaseResponse<InitResponse> response = new BaseResponse<InitResponse>();
		int datumId = 0;
		UserDatumRequest model = request.getbody();
		UserDatum entiy = new UserDatum();
		entiy.setUserId(request.getUserId());
		entiy.setAge(model.getAge());
		entiy.setCityId(model.getCityId());
		entiy.setWeight(model.getWeight());
		entiy.setHeight(model.getHeight());
		entiy.setShape(model.getShape());
		entiy.setSexuat(model.getSexuat());
		entiy.setGender(model.getSex());
		entiy.setSign(model.getSign());
		logger.info("昵称：" + model.getSign());
		// 是否需要更新昵称
		if (!StringUtils.isEmpty(model.getNickName())) {
			User entiyUser = new User();
			entiyUser.setUserId(request.getUserId());
			entiyUser.setNickName(model.getNickName());
			userServiceImpl.updateUser(entiyUser);
		}
		// 查询用户是否存在 存在则更新 不存在添加
		UserDatum data = userDatumService.selectDatumByUserId(request.getUserId());
		if (data != null && data.getDatumId() > 0) {
			// 更新
			datumId = userDatumService.updateDatum(entiy);
		} else {
			datumId = userDatumService.insertDatum(entiy);
		}
		if (datumId <= 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("添加资料失败");
		}
		response.setData(initBusiness.InitUserData(request.getUserId()));
		return response;

	}

	/**
	 * 获取用户资料
	 * 
	 * @param userDatumService
	 * @param user
	 * @return
	 */
	public BaseResponse<UserDatumRequest> GetUserDatum(baseRequest<?> request) {
		BaseResponse<UserDatumRequest> response = new BaseResponse<UserDatumRequest>();
		List<User> data = userServiceImpl.selectDatumByUserId(request.getUserId());
		if (data != null && data.size() > 0) {
			User user = data.get(0);
			UserDatum datum = user.getDatum();
			UserDatumRequest model = new UserDatumRequest();
			model.setAge(datum.getAge());
			model.setSex(datum.getGender());
			model.setCityId(datum.getCityId());
			model.setWeight(datum.getWeight());
			model.setHeight(datum.getHeight());
			model.setShape(datum.getShape());
			model.setSexuat(datum.getSexuat());
			model.setSign(datum.getSign());
			model.setHeadImage(SystemConfig.ImgurlPrefix + user.getHeadImage());
			model.setNickName(user.getNickName());
			response.setData(model);
		}
		return response;

	}

	/**
	 * 更新用户浏览次数
	 * 
	 * @param userPositionService
	 * @param request
	 */
	public void UpdateUserBrowse(UserBrowseServiceImpl userBrowseServiceImpl, int detailId, int userId) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				userServiceImpl.updateBrowseNumber(detailId);
				// 添加浏览记录 也就是访客记录
				UserBrowse browes = new UserBrowse();
				browes.setBrowseId(userId);
				browes.setToUserId(detailId);
				userBrowseServiceImpl.insertBrowse(browes);
			}
		});
		t.start();
	}

	/**
	 * 用户个人主页详情
	 * 
	 * @param userServiceImpl
	 * @param userImgServiceImpl
	 * @param userLableMappingServiceImpl
	 * @param request
	 * @return
	 */
	public BaseResponse<DetailsResponse> GetUserDetails(baseRequest<DetailsRequest> request) {

		BaseResponse<DetailsResponse> response = new BaseResponse<DetailsResponse>();

		DetailsResponse details = new DetailsResponse();
		DetailsUserResponse userBase = new DetailsUserResponse();
		DetailsRequest body = request.getbody();
		//
		RangeParameter rangeParameter = new RangeParameter();
		rangeParameter.setUserId(body.getDetailId());
		// 如果经纬度小于等于0 证明当前位置不可访问 默认天安门
		if (body.getLat() <= 0 || body.getLon() <= 0) {
			body.setLat(ResultEnum.defaultLat);
			body.setLon(ResultEnum.defaultLon);
		}
		rangeParameter.setLat(body.getLat());
		rangeParameter.setLon(body.getLon());
		List<User> userDatas = userServiceImpl.getUserDetails(rangeParameter);
		if (userDatas != null && userDatas.size() > 0) {
			User userData = userDatas.get(0);
			UserDatum datum = userData.getDatum();
			userBase.setHeadImage(SystemConfig.ImgurlPrefix + userData.getHeadImage());
			userBase.setShowId(userData.getId());
			userBase.setNickName(userData.getNickName());
			userBase.setSex(datum.getGender());
			userBase.setAge(datum.getAge());
			userBase.setCityId(datum.getCityId());
			userBase.setCityName(userServiceImpl.SelectProvincesById(userBase.getCityId()).getName());
			userBase.setRange(ResponseUtils.GetRange(datum.getRangeM()));
			userBase.setSign(datum.getSign());
			userBase.setWeight(datum.getWeight());
			userBase.setHeight(datum.getHeight());
			userBase.setShape(datum.getShape());
			userBase.setSexuat(datum.getSexuat());
			userBase.setVip(userData.getUserLevel());
		}
		// 图片

		details.setImgs(GetImgListByUserId(body.getDetailId()));
		// 标签
		UserLableMapping mapping = new UserLableMapping();
		mapping.setUserId(body.getDetailId());
		List<UserLableMapping> userLableData = userLableMappingServiceImpl.selectlabletByUserId(mapping);
		for (UserLableMapping userLableMapping : userLableData) {
			DetailsLableResponse lable = new DetailsLableResponse();
			lable.setLableName(userLableMapping.getLabletTypes().getLableName());
			details.getLables().add(lable);
		}
		// 是否喜欢
		Userlike userlike = new Userlike();
		userlike.setUserId(request.getUserId());
		userlike.setLikeUserId(body.getDetailId());
		Userlike likeData = userlikeServiceImpl.selectUserLikeById(userlike);
		if (likeData != null) {
			userBase.setLike(true);
		}
		details.setUser(userBase);
		response.setData(details);

		UpdateUserBrowse(userBrowseServiceImpl, body.getDetailId(), request.getUserId());
		return response;
	}

	/**
	 * 我的图片
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse<MyImageResponse> GetUserImage(baseRequest<?> request) {
		BaseResponse<MyImageResponse> response = new BaseResponse<MyImageResponse>();
		MyImageResponse myImg = new MyImageResponse();
		myImg.setImgList(GetImgListByUserId(request.getUserId()));
		response.setData(myImg);
		return response;
	}

	/**
	 * 删除图片
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse<?> RemonveImageByImageId(baseRequest<RemoveImgRequest> request) {
		BaseResponse<?> response = new BaseResponse<Object>();

		int result = userImgServiceImpl.DeleteImageByImgId(request.getbody().getImgId());
		if (result <= 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("删除图片失败");
		}
		return response;
	}

	/**
	 * 根据userId获取图片
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserImgResponse> GetImgListByUserId(int userId) {
		List<UserImgResponse> imgList = new ArrayList<UserImgResponse>();
		List<UserImg> userImgs = userImgServiceImpl.selectImgtByUserId(userId);
		if (userImgs != null && userImgs.size() > 0) {
			for (UserImg userImg : userImgs) {
				UserImgResponse img = new UserImgResponse();
				img.setImg(SystemConfig.ImgurlPrefix + userImg.getImagePath());
				img.setImgId(userImg.getImgId());
				imgList.add(img);
			}
		}
		return imgList;
	}

	/**
	 * 用户喜欢和不喜欢
	 * 
	 * @param userlikeServiceImpl
	 * @param request
	 * @return
	 */
	public BaseResponse<UserLikeResponse> UserLike(baseRequest<UserLikeRequest> request) {
		BaseResponse<UserLikeResponse> response = new BaseResponse<UserLikeResponse>();
		int result = 0;
		UserLikeRequest body = request.getbody();
		Userlike like = new Userlike();
		like.setUserId(request.getUserId());
		like.setLikeUserId(body.getLikeId());
		UserLikeResponse responseData = new UserLikeResponse();
		if (body.getType() == 1)// 喜欢
		{
			// 先查询是否存在
			Userlike likeData = userlikeServiceImpl.selectUserLikeById(like);
			if (likeData == null) {
				result = userlikeServiceImpl.insertUserlike(like);
				responseData.setType(1);
			} else {
				response.setCode(ResultEnum.ServiceErrorCode);
				response.setMsg("此用户您已经喜欢、请勿重复喜欢");
				return response;
			}

		} else {
			// 不喜欢
			result = userlikeServiceImpl.deleteUserlike(like);
			responseData.setType(2);
		}
		if (result <= 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("操作失败");
			return response;
		}
		response.setData(responseData);
		return response;
	}

	/**
	 * 喜欢列表
	 * 
	 * @param userlikeServiceImpl
	 * @param request
	 * @return
	 */
	public BaseResponse<List<HomeResponse>> UserLikeList(baseRequest<?> request) {
		BaseResponse<List<HomeResponse>> response = new BaseResponse<List<HomeResponse>>();
		List<HomeResponse> list = new ArrayList<HomeResponse>();
		List<User> userData = userServiceImpl.userLikeList(request.getUserId());
		for (User user : userData) {
			list.add(businessUtils.EntityToModel(user));
		}
		response.setData(list);
		return response;
	}

	/**
	 * 获取最近访客
	 * 
	 * @param userBrowseServiceImpl
	 * @param request
	 * @return
	 */
	public BaseResponse<?> GetUserBrowse(baseRequest<BrowseRequest> request) {
		BaseResponse<Object> response = new BaseResponse<Object>();
		BrowseRequest body = request.getbody();
		// 返回数量
		if (body.getType() == 1) {
			BrowseNumberResponse numberData = new BrowseNumberResponse();
			int number = userBrowseServiceImpl.selectBrowseByNumber(request.getUserId());
			numberData.setBrowseNumber(number);
			response.setData(numberData);
		} else if (body.getType() == 2) {
			List<HomeResponse> browesResponse = new ArrayList<HomeResponse>();
			RangeParameter rangeParameter = PageParameter.GetRangePage(body.getPageIndex(), request.getUserId());
			// 数据
			List<User> browesData = userServiceImpl.userBrowseList(rangeParameter);
			for (User user : browesData) {
				browesResponse.add(businessUtils.EntityToModel(user));
			}
			response.setData(browesResponse);
		}
		return response;
	}

	public Map<String, String> GetMessage(baseRequest<PhoneMsgRequest> request, BaseResponse<?> output, String code) {
		Map<String, String> map = new HashMap<String, String>();
		String phone = "";// 手机号
		String msg = "";// 消息
		PhoneMsgRequest body = request.getbody();
		// 注册
		if (body.getType() == 1) {

			if (!StringUtils.isEmpty(request.getbody().getPhone())) {
				phone = request.getbody().getPhone();
				msg = "欢迎加入欲见，验证码为" + code + "，一分钟内有效";

			} else {
				output.setCode(ResultEnum.ColmunErrorCode);
				output.setMsg("手机号为空");
				return null;
			}
		}
		// 2绑定支付宝账号
		// 3提现
		else if ((body.getType() == 2 || body.getType() == 3) && request.getUserId() > 0) {
			// 根据用户ID获取用户手机号
			User userData = userServiceImpl.selectUserByUserId(request.getUserId());
			if (userData != null && userData.getUserId() > 0) {
				phone = userData.getPhone();
				if (body.getType() == 2) {
					msg = "您正在绑定支付宝账号;验证码为" + code + "，一分钟内有效";
				}
				// 3提现
				else {
					msg = "您在平台提现验证码为" + code + "，一分钟内有效";
				}

			}
		} else {
			return null;
		}
		map.put("phone", phone);
		map.put("msg", msg);
		return map;
	}

	public void test() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				System.out.println("1111111111111111");
			}
		});
		t.start();

	}

}
