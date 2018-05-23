package com.api.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.NullLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.api.request.BrowseRequest;
import com.api.request.DetailsRequest;
import com.api.request.InformRequest;
import com.api.request.LableRequest;
import com.api.request.LableRequestData;
import com.api.request.LikeListRequest;
import com.api.request.PhoneMsgRequest;
import com.api.request.RemoveBrowseRequest;
import com.api.request.RemoveImgRequest;
import com.api.request.UserDatumRequest;
import com.api.request.UserLikeRequest;
import com.api.request.baseRequest;
import com.api.request.userModel;
import com.api.response.BrowseNumberResponse;
import com.api.response.DetailsLableResponse;
import com.api.response.DetailsResponse;
import com.api.response.HomeResponse;
import com.api.response.InitResponse;
import com.api.response.LableResponse;
import com.api.response.LableTypeResponse;
import com.api.response.LikeListResponse;
import com.api.response.MyImageResponse;
import com.api.response.MyLableResponse;
import com.api.response.DetailsUserResponse;
import com.api.response.UserLikeResponse;
import com.api.response.UserPwdResponse;
import com.api.response.BaseResponse;
import com.api.response.BrowesList;
import com.api.response.BrowesResponse;
import com.api.utils.PageParameter;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.service.api.impl.InvitationCodeServiceImpl;
import com.service.api.impl.ReportServiceImpl;
import com.service.api.impl.UserBrowseExtServiceImpl;
import com.service.api.impl.UserBrowseServiceImpl;
import com.service.api.impl.UserDatumServiceImpl;
import com.service.api.impl.UserImgServiceImpl;
import com.service.api.impl.UserInviteServiceImpl;
import com.service.api.impl.UserLableMappingServiceImpl;
import com.service.api.impl.UserServiceImpl;
import com.service.api.impl.UserVerifyCodeServiceImpl;
import com.service.api.impl.UserlikeServiceImpl;
import com.service.bean.AppHomePagePaging;
import com.service.bean.InvitationCode;
import com.service.bean.LabletType;
import com.service.bean.RangeParameter;
import com.service.bean.Inform;
import com.service.bean.User;
import com.service.bean.UserBrowse;
import com.service.bean.UserBrowseExt;
import com.service.bean.UserDatum;
import com.service.bean.UserImg;
import com.service.bean.UserInvite;
import com.service.bean.UserLableMapping;
import com.service.bean.UserVerifyCode;
import com.service.bean.Userlike;
import com.service.easemob.EaseMobBusiness;
import com.service.easemob.NeteaseBusiness;
import com.service.easemob.NeteaseModel;
import com.service.enums.LableType;
import com.service.utils.Md5Util;
import com.service.utils.StringUtils;
import com.service.utils.SystemConfig;

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
	@Autowired
	private UserBrowseExtServiceImpl userBrowseExtServiceImpl;
	@Autowired
	private ReportServiceImpl reportServiceImpl;

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
	public BaseResponse<MyLableResponse> AddUserLable(baseRequest<LableRequestData> request) {
		BaseResponse<MyLableResponse> response = new BaseResponse<MyLableResponse>();

		List<LableRequest> lableRequestDatas = request.getbody().getList();
		// 先删除后添加
		List<LableRequest> addList = new ArrayList<LableRequest>();
		// 取出传过来isMyLable为true的标签
		Predicate<LableRequest> con = n -> n.isMyLable() == true;
		lableRequestDatas.stream().filter(con).forEach(p -> {
			addList.add(p);
		});

		if (addList == null || addList.size() <= 0) {
			response.setCode(ResultEnum.VerificationCode);
			response.setMsg("标签不能为空");
			return response;
		}
		// 删除
		userLableMappingServiceImpl.deleteLableByUserId(request.getUserId());
		if (addList != null && addList.size() > 0) {
			// 添加
			List<UserLableMapping> entitys = new ArrayList<UserLableMapping>();
			for (LableRequest item : addList) {
				UserLableMapping mapping = new UserLableMapping();
				mapping.setUserId(request.getUserId());
				mapping.setLableId(item.getLableId());
				mapping.setLableType(item.getLableType());
				entitys.add(mapping);
			}
			int result = userLableMappingServiceImpl.insertlabletMapping(entitys);
			if (result > 0) {
				response = GetUserLableByUserID(request);
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
	public BaseResponse<MyLableResponse> GetUserLableByUserID(baseRequest<?> request) {
		BaseResponse<MyLableResponse> response = new BaseResponse<MyLableResponse>();

		MyLableResponse myLableResponse = new MyLableResponse();
		// 获取用户已选择的标签

		UserLableMapping mapping = new UserLableMapping();
		mapping.setUserId(request.getUserId());
		List<UserLableMapping> userLableData = userLableMappingServiceImpl.selectlabletByUserId(mapping);
		// 获取所有标签

		List<LabletType> labletTypes = initBusiness.GetLableTypeAll();
		LableType[] laEnum = LableType.values();
		for (LableType el : laEnum) {

			LableTypeResponse typeResponse = new LableTypeResponse();
			List<LableResponse> lableRespose = new ArrayList<LableResponse>();
			typeResponse.setLabelTypeName(el.getDesc());
			Predicate<LabletType> contain = n -> n.getLableType() == el.getStateCode();

			labletTypes.stream().filter(contain).forEach(P -> {
				LableResponse lableResponse = new LableResponse();
				lableResponse.setLableId(P.getLableId());
				lableResponse.setLableName(P.getLableName());
				lableResponse.setLableType(P.getLableType());
				lableResponse.setMyLable(false);

				if (userLableData != null) {
					Optional<UserLableMapping> mp = userLableData.stream().filter(o -> o.getLableId() == P.getLableId())
							.findFirst();

					if (mp.isPresent()) {
						lableResponse.setMyLable(true);
						if (!StringUtils.isEmpty(typeResponse.getDisplayLable())) {
							typeResponse.setDisplayLable(
									typeResponse.getDisplayLable() + "," + lableResponse.getLableName());
						} else {
							typeResponse.setDisplayLable(lableResponse.getLableName());
						}

					}

				}
				lableRespose.add(lableResponse);
			});
			typeResponse.setLableTypes(lableRespose);
			myLableResponse.getLables().add(typeResponse);
		}
		response.setData(myLableResponse);
		return response;
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse<InitResponse> userRegister(baseRequest<userModel> request) {
		// 检查手机号是否重复
		// 检查邀请码是否正确
		// 检查短信验证码
		userModel model = request.getbody();
		BaseResponse<InitResponse> response = new BaseResponse<InitResponse>();
		User userData = userServiceImpl.selectUserByphone(model.getPhone());
		if (userData != null) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("手机号已存在！请登录");
			return response;
			// 如果已存在 是否是导入用户
			// 2017-10-31注掉以下逻辑、因为导入用户始终是导入用户 导入获取不了手机号
			// if (userData.getIsImport()==1) {
			//
			// //导入用户走此逻辑
			// return SetImportUser(request,userData);
			// }
			// else
			// {

			// }

		}
		// 如果邀请码为空设置默认邀请码
		if (StringUtils.isEmpty(model.getInviteCode())) {

			model.setInviteCode("123456");
			if ("release".equals(SystemConfig.EnvIdentity)) {
				model.setInviteCode("998585");
			}
		}
		User codeData = userServiceImpl.selectUserByInviteCode(model.getInviteCode());
		if (codeData == null) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("邀请码不正确");
			return response;
		}
		UserVerifyCode userCode = userVerifyCodeServiceImpl.selectCodeByphone(model.getPhone());
		if (userCode == null || !userCode.getVerifyCode().equals(model.getVerifyCode())) {
			response.setCode(ResultEnum.VerificationCode);
			response.setMsg("手机验证码不正确");
			return response;
		}
		// 注册
		User userEntity = new User();
		userEntity.setPhone(model.getPhone());
		userEntity.setDeviceType(request.getDeviceType());
		userEntity.setDeviceToken(request.getDeviceToken());
		userEntity.setUserSource(request.getDeviceType());// 默认全部是APP
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
		SetNeteaseToken(userEntity.getUserId());
		InitResponse initUser = initBusiness.InitUserData(userEntity.getUserId());
		response.setData(initUser);
		AddUserInvite(request, codeData, userEntity.getUserId());
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
		UserVerifyCode userCode = userVerifyCodeServiceImpl.selectCodeByphone(model.getPhone());
		if (userCode == null || !userCode.getVerifyCode().equals(model.getVerifyCode())) {
			response.setCode(ResultEnum.VerificationCode);
			response.setMsg("手机验证码不正确");
			return response;
		}
		// 修改密码
		User userEntity = new User();
		userEntity.setPassWord(Md5Util.stringByMD5(model.getPassWord()));
		userEntity.setUserId(user.getUserId());
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
			response.setCode(ResultEnum.VerificationCode);
			response.setMsg("用户名或密码错误");
			return response;
		}
		else {
			if (resultUser.getIsDisable()<=0) {
				response.setCode(ResultEnum.VerificationCode);
				response.setMsg("此用户上传有关色情图片 已被禁用");
				return response;
			}
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
	public void AddUserInvite(baseRequest<?> request, User invitation, int registerId) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				// 添加邀请
				UserInvite invite = new UserInvite();
				invite.setInviteUserId(invitation.getUserId());
				invite.setInviteCode(invitation.getInviteCode());
				invite.setRegisterUserId(registerId);
				userInviteServiceImpl.insertInvite(invite);
				// 添加经纬度
				businessUtils.AddUserPoint(request, registerId);
				// 添加浏览主表 用于首页查询排序、统计浏览次数
				UserBrowseExt ext = new UserBrowseExt();
				ext.setUserId(registerId);
				ext.setBrowseNumber(0);
				userBrowseExtServiceImpl.insertBrowseExt(ext);
				// 注册环信----改用网易云im
				String easemobId = registerId + SystemConfig.EaseSuffixId;
				// String result = EaseMobBusiness.AccountCreate(easemobId);
				// Map map = (Map) JSON.parse(result);
				NeteaseModel netease = NeteaseBusiness.CreateaccId(easemobId,registerId);
				// if (map != null && !map.containsKey("error")) {
				if (netease != null && netease.getCode() == 200) {
					// 更新用户
					User upUser = new User();
					upUser.setUserId(registerId);
					upUser.setEasemobId(easemobId);
					upUser.setImToken(netease.getInfo().getToken());
					upUser.setIsEasemob(1);
					userServiceImpl.updateUser(upUser);

				}

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
		if (StringUtils.isEmpty(model.getSign())) {
			model.setSign(ResultEnum.defaultSign);
		}
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
			if (user.getHeadImage().indexOf("http") != -1) {
				model.setHeadImage(user.getHeadImage());
			}
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
	public void UpdateUserBrowse(int detailId, int userId) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				// 不是自己浏览自己个人空间 增加浏览记录
				if (detailId != userId) {
					userBrowseExtServiceImpl.updateBrowesExt(detailId);
					UserBrowse browes = new UserBrowse();
					browes.setBrowseId(userId);
					browes.setToUserId(detailId);
					UserBrowse brResult = userBrowseServiceImpl.selectExistRecord(browes);
					if (brResult != null) {
						browes.setKeyId(brResult.getKeyId());
						browes.setBrowseDate(new Date());
						userBrowseServiceImpl.updateBrowesCount(browes);
					} else {
						// 添加浏览记录 也就是访客记录
						userBrowseServiceImpl.insertBrowse(browes);
					}
				}
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
		if (request.getLat() <= 0 || request.getLon() <= 0) {
			request.setLat(ResultEnum.defaultLat);
			request.setLon(ResultEnum.defaultLon);
		}
		rangeParameter.setLat(request.getLat());
		rangeParameter.setLon(request.getLon());
		List<User> userDatas = userServiceImpl.getUserDetails(rangeParameter);
		if (userDatas != null && userDatas.size() > 0) {
			User userData = userDatas.get(0);
			UserDatum datum = userData.getDatum();
			userBase.setHeadImage(SystemConfig.ImgurlPrefix + userData.getHeadImage());
			if (userData.getHeadImage().indexOf("http") != -1) {
				userBase.setHeadImage(userData.getHeadImage());
			}
			userBase.setShowId(userData.getUserNo());
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
			userBase.setEaseId(body.getDetailId() + SystemConfig.EaseSuffixId);
			userBase.setImToken(userData.getImToken());
		}
		// 图片

		details.setImgs(businessUtils.GetImgListByUserId(body.getDetailId()));
		// 标签
		UserLableMapping mapping = new UserLableMapping();
		mapping.setUserId(body.getDetailId());
		List<UserLableMapping> userLableData = userLableMappingServiceImpl.selectlabletByUserId(mapping);
		for (UserLableMapping userLableMapping : userLableData) {
			DetailsLableResponse lable = new DetailsLableResponse();
			lable.setLableName(userLableMapping.getLabletTypes().getLableName());
			lable.setLabelId(userLableMapping.getLableId());
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

		UpdateUserBrowse(body.getDetailId(), request.getUserId());
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
		myImg.setImgList(businessUtils.GetImgListByUserId(request.getUserId()));
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

		int result = userImgServiceImpl.UpdateImageByImgId(request.getbody().getImgId());
		if (result <= 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("删除图片失败");
		}
		return response;
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
				response.setCode(ResultEnum.VerificationCode);
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
	public BaseResponse<LikeListResponse> UserLikeList(baseRequest<LikeListRequest> request) {
		BaseResponse<LikeListResponse> response = new BaseResponse<LikeListResponse>();
		response.setData(new LikeListResponse());
		List<HomeResponse> list = new ArrayList<HomeResponse>();
		LikeListRequest body = request.getbody();
		if (body == null || body.getPageIndex() <= 0) {
			response.setCode(ResultEnum.VerificationCode);
			response.setMsg("页码为空");
			return response;
		}
		AppHomePagePaging pagePaging = PageParameter.GetLikeListPage(body.getPageIndex(), request.getUserId());
		List<User> userData = userServiceImpl.userLikeList(pagePaging);
		for (User user : userData) {
			list.add(businessUtils.EntityToModel(user));
		}
		response.getData().setList(list);
		// response.setData(list);
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
			BrowesList list = new BrowesList();
			List<BrowesResponse> browesResponse = new ArrayList<BrowesResponse>();
			RangeParameter rangeParameter = PageParameter.GetRangePage(body.getPageIndex(), request.getUserId());
			// 数据
			List<User> browesData = userServiceImpl.userBrowseList(rangeParameter);
			for (User user : browesData) {
				browesResponse.add(businessUtils.BrowesEntityToModel(user));
			}
			list.setList(browesResponse);
			if (body.getPageIndex() == 1) {
				// 只有第一页的时候 更新所有浏览用户 已被查看
				userBrowseServiceImpl.updateBrowesIsBrowse(request.getUserId());
			}
			response.setData(list);
		}
		return response;
	}

	/**
	 * 删除访客
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse<?> RemoveBrowse(baseRequest<RemoveBrowseRequest> request) {
		BaseResponse<?> response = new BaseResponse<Object>();
		RemoveBrowseRequest body = request.getbody();
		if (body.getBrowseId() <= 0) {
			response.setCode(ResultEnum.ColmunErrorCode);
			response.setMsg("请求id为空");
			return response;
		}
		int result = userBrowseServiceImpl.deleteBrowsById(body.getBrowseId());
		if (result <= 0) {
			response.setCode(ResultEnum.VerificationCode);
			response.setMsg("服务器无此记录");
		}
		return response;
	}

	public Map<String, String> GetMessage(baseRequest<PhoneMsgRequest> request, BaseResponse<?> output, String code) {
		Map<String, String> map = new HashMap<String, String>();
		String phone = request.getbody().getPhone();// 手机号
		String msg = "";// 消息
		PhoneMsgRequest body = request.getbody();
		// 注册
		if (body.getType() == 1) {
			msg = "欢迎加入欲见，验证码为" + code + "，一分钟内有效";
		}
		// 2绑定支付宝账号
		// 3提现
		else if (body.getType() == 2) {
			msg = "您正在绑定支付宝账号;验证码为" + code + "，一分钟内有效";
		} else if (body.getType() == 3) // 3提现
		{
			msg = "您在平台提现验证码为" + code + "，一分钟内有效";
		} else if (body.getType() == 4) // 修改密码
		{
			msg = "您修改密码验证码为" + code + "，一分钟内有效";
		} else if (body.getType() == 5) // 忘记密码
		{
			msg = "您忘记密码验证码为" + code + "，一分钟内有效";
		}

		else {
			return null;
		}
		map.put("phone", phone);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 导入用户更新资料
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	private BaseResponse<InitResponse> SetImportUser(baseRequest<userModel> request, User user) {
		BaseResponse<InitResponse> response = new BaseResponse<InitResponse>();
		userModel body = request.getbody();
		// 更新用户deviceType,deviceToken，userSource，passWord
		// 更新坐标
		User codeData = userServiceImpl.selectUserByInviteCode(body.getInviteCode());
		if (codeData == null) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("邀请码不正确");
			return response;
		}

		// 添加邀请记录
		UserInvite invite = new UserInvite();
		invite.setInviteUserId(codeData.getUserId());
		invite.setInviteCode(codeData.getInviteCode());
		invite.setRegisterUserId(user.getUserId());
		userInviteServiceImpl.insertInvite(invite);

		// 更新导入用户信息
		User entity = new User();
		entity.setUserId(user.getUserId());
		entity.setDeviceToken(request.getDeviceToken());
		entity.setDeviceType(request.getDeviceType());
		entity.setUserSource(request.getDeviceType());
		entity.setInviteCode(body.getInviteCode());
		entity.setPassWord(Md5Util.stringByMD5(body.getPassWord()));
		entity.setIsImport(0);// 更新为不是导入用户
		int result = userServiceImpl.updateImportUser(entity);
		if (result > 0) {
			// 更新经纬度
			businessUtils.SetUserPosition(request, user.getUserId());
		}
		// 获取用户信息
		InitResponse initUser = initBusiness.InitUserData(user.getUserId());
		response.setData(initUser);
		return response;
	}

	/**
	 * 举报用户
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse<?> UserInform(baseRequest<InformRequest> request) {
		BaseResponse<?> response = new BaseResponse<Object>();
		Inform report = new Inform();
		InformRequest body = request.getbody();
		if (body == null || StringUtils.isEmpty(body.getReason())) {
			response.setMsg("参数为空");
			response.setCode(ResultEnum.ColmunErrorCode);
			return response;
		}
		report.setInformUserId(request.getUserId());
		report.setBeingInformId(body.getDetailId());
		report.setContent("");
		report.setReason(body.getReason());
		int result = reportServiceImpl.insertReport(report);
		if (result <= 0) {
			response.setMsg("服务器错误");
			response.setCode(ResultEnum.ServiceErrorCode);
		}
		return response;

	}

	/**
	 * 设置网易云token
	 * 
	 * @param userId
	 */
	public void SetNeteaseToken(int userId) {
		// 此处设置网易云IM登录token
		User up_user = new User();
		up_user.setUserId(userId);
		String token = userId+ SystemConfig.EaseSuffixId;
		up_user.setImToken(token);
		userServiceImpl.updateUser(up_user);
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
