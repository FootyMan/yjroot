package com.api.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.api.request.BrowseRequest;
import com.api.request.DetailsRequest;
import com.api.request.InitUserRequest;
import com.api.request.PhoneMsgRequest;
import com.api.request.UserDatumRequest;
import com.api.request.UserLikeRequest;
import com.api.request.baseRequest;
import com.api.request.userModel;
import com.api.response.BrowseNumberResponse;
import com.api.response.DetailsImgResponse;
import com.api.response.DetailsLableResponse;
import com.api.response.DetailsResponse;
import com.api.response.HomeResponse;
import com.api.response.InitResponse;
import com.api.response.InitResponseAppData;
import com.api.response.LableResponse;
import com.api.response.PageTwoResponse;
import com.api.response.DetailsUserResponse;
import com.api.response.UserInfoResponse;
import com.api.response.UserLikeResponse;
import com.api.response.UserPwdResponse;
import com.api.response.VersionResponse;
import com.api.response.baseResponse;
import com.api.utils.ExceptionHandler;
import com.api.utils.PageParameter;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.myErp.impl.AppversionServiceImpl;
import com.myErp.impl.InvitationCodeServiceImpl;
import com.myErp.impl.ProvinceServiceImpl;
import com.myErp.impl.UserBrowseServiceImpl;
import com.myErp.impl.UserDatumServiceImpl;
import com.myErp.impl.UserImgServiceImpl;
import com.myErp.impl.UserInviteServiceImpl;
import com.myErp.impl.UserLableMappingServiceImpl;
import com.myErp.impl.UserPositionServiceImpl;
import com.myErp.impl.UserServiceImpl;
import com.myErp.impl.UserVerifyCodeServiceImpl;
import com.myErp.impl.UserlikeServiceImpl;
import com.myErp.manager.bean.Appversion;
import com.myErp.manager.bean.InvitationCode;
import com.myErp.manager.bean.LabletType;
import com.myErp.manager.bean.Province;
import com.myErp.manager.bean.RangeParameter;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserBrowse;
import com.myErp.manager.bean.UserDatum;
import com.myErp.manager.bean.UserImg;
import com.myErp.manager.bean.UserInvite;
import com.myErp.manager.bean.UserLableMapping;
import com.myErp.manager.bean.UserPosition;
import com.myErp.manager.bean.UserVerifyCode;
import com.myErp.manager.bean.Userlike;
import com.myErp.redis.CityRedisManager;
import com.myErp.utils.Md5Util;
import com.myErp.utils.StringUtils;
import com.myErp.utils.SystemConfig;

public class UserBusiness {

	private static Logger logger = Logger.getLogger(UserBusiness.class);

	private UserBusiness() {
	}

	private static volatile UserBusiness instance = null;

	public static UserBusiness getInstance() {
		if (instance == null) {
			synchronized (UserBusiness.class) {
				if (instance == null) {
					instance = new UserBusiness();
				}
			}
		}
		return instance;
	}

	/**
	 * 初始化用户
	 * 
	 * @param user
	 * @return
	 */
	public baseResponse<InitResponse> initUser(UserPositionServiceImpl userPositionService, baseRequest<?> user) {

		baseResponse<InitResponse> response = new baseResponse<InitResponse>();
		UserPosition position = new UserPosition();
		position.setIsPosition(1);
		if (user.getLon() <= 0) {
			user.setLon(ResultEnum.defaultLon);
		}
		if (user.getLat() <= 0) {
			user.setLat(ResultEnum.defaultLat);
			position.setIsPosition(0);
		}
		
		position.setUserId(user.getUserId());
		position.setLongitude(user.getLon());
		position.setLatitude(user.getLat());
		int positionId = userPositionService.updatePosition(position);
		if (positionId <= 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("坐标拾取失败");
			return response;
		}
		// }
		return response;
	}

	/**
	 * 获取版本信息用于提示更新
	 * 
	 * @param appversionService
	 * @param deviceType
	 * @param versionCode
	 * @return
	 */
	public VersionResponse GetAppVersion(AppversionServiceImpl appversionService, int deviceType, int versionCode) {
		VersionResponse versionModel = new VersionResponse();
		Appversion version = appversionService.selectVersionByDevice(deviceType);
		if (version != null && version.getVersionCode() > versionCode) {
			// 提示更新
			versionModel.setExistUp(true);
			versionModel.setDownloadUrl(version.getDownloadUrl());
			versionModel.setUpdateDesc(version.getUpdateDescription());
			versionModel.setVersionCode(version.getVersionCode());
			versionModel.setVersionName(version.getVersionName());
		}
		return versionModel;
	}

	/**
	 * 获取二次启动页
	 * 
	 * @return
	 */
	public PageTwoResponse GetPageTwo() {
		PageTwoResponse pageTwoModel = new PageTwoResponse();
		pageTwoModel.setImgUrl(SystemConfig.pageTwo);
		return pageTwoModel;
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public baseResponse<InitResponse> userRegister(UserServiceImpl userServiceImpl,
			UserVerifyCodeServiceImpl userVerifyCodeServiceImpl, InvitationCodeServiceImpl invitationCodeServiceImpl,
			UserInviteServiceImpl userInviteServiceImpl, baseRequest<userModel> user) {
		// 检查手机号是否重复
		// 检查邀请码是否正确
		// 检查短信验证码
		userModel model = user.getbody();
		baseResponse<InitResponse> response = new baseResponse<InitResponse>();
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
		InitResponse initUser = InitUserData(userServiceImpl, userEntity.getUserId());
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
	public baseResponse UpdateUserPwd(UserServiceImpl userServiceImpl,
			UserVerifyCodeServiceImpl userVerifyCodeServiceImpl, baseRequest<UserPwdResponse> user) {
		// 检查手机号是否重复
		// 检查邀请码是否正确
		// 检查短信验证码
		UserPwdResponse model = user.getbody();
		baseResponse response = new baseResponse();
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
	public baseResponse<InitResponse> userLogin(UserServiceImpl userServiceImpl, baseRequest<userModel> user) {
		// 检查手机号是否重复
		// 检查邀请码是否正确
		// 检查短信验证码
		userModel model = user.getbody();
		baseResponse<InitResponse> response = new baseResponse<InitResponse>();
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
		InitResponse initUser = InitUserData(userServiceImpl, resultUser.getUserId());
		response.setData(initUser);
		return response;

	}

	/**
	 * 开启线程去添加用户坐标
	 * 
	 * @param userPositionService
	 * @param request
	 */
	public void AddUserPoint(UserPositionServiceImpl userPositionService, baseRequest request,int userId) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				UserPosition position = new UserPosition();
				position.setIsPosition(1);
				if (request.getLon() <= 0) {
					request.setLon(ResultEnum.defaultLon);
				}
				if (request.getLat() <= 0) {
					request.setLat(ResultEnum.defaultLat);
					position.setIsPosition(0);
				}
				position.setUserId(userId);
				position.setLongitude(request.getLon());
				position.setLatitude(request.getLat());
				userPositionService.insertPosition(position);
			}
		});
		t.start();
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
	public baseResponse<InitResponse> AddUserDatum(UserServiceImpl userServiceImpl,
			UserDatumServiceImpl userDatumService, baseRequest<UserDatumRequest> request) {
		baseResponse<InitResponse> response = new baseResponse<InitResponse>();
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
		response.setData(InitUserData(userServiceImpl, request.getUserId()));
		return response;

	}

	/**
	 * 获取用户资料
	 * 
	 * @param userDatumService
	 * @param user
	 * @return
	 */
	public baseResponse<UserDatumRequest> GetUserDatum(UserServiceImpl userServiceImpl, baseRequest<?> request) {
		baseResponse<UserDatumRequest> response = new baseResponse<UserDatumRequest>();
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
			model.setHeadImage(SystemConfig.Imgurl+user.getHeadImage());
			model.setNickName(user.getNickName());
			response.setData(model);
		}
		return response;

	}

	/**
	 * 对象转换Model
	 * 
	 * @param labletTypes
	 * @return
	 */
	public InitResponseAppData LableEntityToModel(List<LabletType> labletTypes) {
		InitResponseAppData appData = new InitResponseAppData();
		for (LabletType labletType : labletTypes) {
			LableResponse lableModel = new LableResponse();
			lableModel.setLableId(labletType.getLableId());
			lableModel.setLableName(labletType.getLableName());
			// 1个性类
			if (labletType.getLableType() == 1) {
				lableModel.setLableType(labletType.getLableType());
				appData.getPersonality().add(lableModel);
			}
			// 2运动类
			if (labletType.getLableType() == 2) {
				lableModel.setLableType(labletType.getLableType());
				appData.getSports().add(lableModel);
			}
			// 3音乐类
			if (labletType.getLableType() == 3) {
				lableModel.setLableType(labletType.getLableType());
				appData.getMusic().add(lableModel);
			}
			// 4美食类
			if (labletType.getLableType() == 4) {
				lableModel.setLableType(labletType.getLableType());
				appData.getCate().add(lableModel);
			}
			// 5旅游类
			if (labletType.getLableType() == 5) {
				lableModel.setLableType(labletType.getLableType());
				appData.getTour().add(lableModel);
			}
		}
		return appData;
	}

	/**
	 * 更新用户浏览次数
	 * 
	 * @param userPositionService
	 * @param request
	 */
	public void UpdateUserBrowse(UserServiceImpl userServiceImpl, UserBrowseServiceImpl userBrowseServiceImpl,
			int detailId, int userId) {
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
	public baseResponse<DetailsResponse> GetUserDetails(UserServiceImpl userServiceImpl,
			UserImgServiceImpl userImgServiceImpl, UserLableMappingServiceImpl userLableMappingServiceImpl,
			UserlikeServiceImpl userlikeServiceImpl, UserBrowseServiceImpl userBrowseServiceImpl,
			baseRequest<DetailsRequest> request) {

		baseResponse<DetailsResponse> response = new baseResponse<DetailsResponse>();

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
			userBase.setHeadImage(SystemConfig.Imgurl+userData.getHeadImage());
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
		List<UserImg> userImgs = userImgServiceImpl.selectImgtByUserId(body.getDetailId());
		if (userImgs != null && userImgs.size() > 0) {
			for (UserImg userImg : userImgs) {
				DetailsImgResponse img = new DetailsImgResponse();
				img.setImg(userImg.getImagePath());
				details.getImgs().add(img);
			}

		}
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

		UserBusiness.getInstance().UpdateUserBrowse(userServiceImpl, userBrowseServiceImpl, body.getDetailId(),
				request.getUserId());
		return response;
	}

	/**
	 * 用户喜欢和不喜欢
	 * 
	 * @param userlikeServiceImpl
	 * @param request
	 * @return
	 */
	public baseResponse<UserLikeResponse> UserLike(UserlikeServiceImpl userlikeServiceImpl,
			baseRequest<UserLikeRequest> request) {
		baseResponse<UserLikeResponse> response = new baseResponse<UserLikeResponse>();
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
	public baseResponse<List<HomeResponse>> UserLikeList(UserServiceImpl userServiceImpl, baseRequest<?> request) {
		baseResponse<List<HomeResponse>> response = new baseResponse<List<HomeResponse>>();
		List<HomeResponse> list = new ArrayList<HomeResponse>();
		List<User> userData = userServiceImpl.userLikeList(request.getUserId());
		for (User user : userData) {
			list.add(EntityToModel(user));
		}
		response.setData(list);
		return response;
	}

	/**
	 * 初始化用户信息
	 * 
	 * @param userServiceImpl
	 * @param userId
	 * @return
	 */
	public InitResponse InitUserData(UserServiceImpl userServiceImpl, int userId) {
		InitResponse initUser = new InitResponse();
		UserInfoResponse info = new UserInfoResponse();
		List<User> userDatas = userServiceImpl.initUser(userId);
		if (userDatas != null && userDatas.size() > 0) {
			User user = userDatas.get(0);
			UserDatum datum = user.getDatum();

			info.setUserId(userId);
			info.setShowId(user.getId());
			info.setHeadImage(SystemConfig.Imgurl+user.getHeadImage());
			info.setNickName(user.getNickName());
			info.setSex(datum.getGender());
			info.setAge(datum.getAge());
			// 根据城市Id转换具体城市 TODO:需要优化成缓存
			// prv
			// new CityRedisManager().GetCitySingle(datum.getCity());
			info.setCityId(datum.getCityId());
			info.setCityName(userServiceImpl.SelectProvincesById(info.getCityId()).getName());
			info.setSign(datum.getSign());
			info.setWeight(datum.getWeight());
			info.setHeight(datum.getHeight());
			info.setShape(datum.getShape());
			info.setSexuat(datum.getSexuat());
			info.setVip(user.getUserLevel());
			info.setInviteCode(user.getInviteCode());
			info.setFull(true);
		} else {
			User user = userServiceImpl.selectUserByUserId(userId);
			if (user != null) {
				info.setUserId(userId);
				info.setShowId(user.getId());
				info.setNickName(user.getNickName());
				info.setVip(user.getUserLevel());
				info.setFull(false);
			}
		}
		initUser.setUser(info);
		return initUser;
	}

	/**
	 * 获取最近访客
	 * 
	 * @param userBrowseServiceImpl
	 * @param request
	 * @return
	 */
	public baseResponse<?> GetUserBrowse(UserBrowseServiceImpl userBrowseServiceImpl, UserServiceImpl userServiceImpl,
			baseRequest<BrowseRequest> request) {
		baseResponse<Object> response = new baseResponse<Object>();
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
				browesResponse.add(EntityToModel(user));
			}
			response.setData(browesResponse);
		}
		return response;
	}

	public Map<String, String> GetMessage(UserServiceImpl userServiceImpl, baseRequest<PhoneMsgRequest> request,
			baseResponse<?> output, String code) {
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

	/**
	 * 实体对象转换model
	 * 
	 * @param user
	 * @return
	 */
	public HomeResponse EntityToModel(User user) {
		HomeResponse model = new HomeResponse();
		model.setUserId(user.getUserId());
		model.setNickName(user.getNickName());
		model.setVip(user.getUserLevel());
		model.setHeadImage(SystemConfig.Imgurl+user.getHeadImage());
		model.setSex(user.getDatum().getGender());
		model.setAge(user.getDatum().getAge());
		model.setSign(user.getDatum().getSign());
		// 访问时间设置
		if (user.getBrowseDate() != null) {
			model.setBrowseData(ResponseUtils.GetBrowseTime(user.getBrowseDate()));
		}
		return model;
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
