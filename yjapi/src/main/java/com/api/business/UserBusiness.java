package com.api.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.model.DetailsImgResponseModel;
import com.api.model.DetailsLableResponseModel;
import com.api.model.DetailsRequestModel;
import com.api.model.DetailsResponseModel;
import com.api.model.InitResponseAppData;
import com.api.model.InitUserRequestModel;
import com.api.model.LableResponseModel;
import com.api.model.PageTwoResponseModel;
import com.api.model.UserDatumRequestModel;
import com.api.model.UserPwdResponseModel;
import com.api.model.VersionResponseModel;
import com.api.model.baseRequest;
import com.api.model.baseResponse;
import com.api.model.userModel;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.myErp.impl.AppversionServiceImpl;
import com.myErp.impl.InvitationCodeServiceImpl;
import com.myErp.impl.UserDatumServiceImpl;
import com.myErp.impl.UserImgServiceImpl;
import com.myErp.impl.UserInviteServiceImpl;
import com.myErp.impl.UserLableMappingServiceImpl;
import com.myErp.impl.UserPositionServiceImpl;
import com.myErp.impl.UserServiceImpl;
import com.myErp.impl.UserVerifyCodeServiceImpl;
import com.myErp.manager.bean.Appversion;
import com.myErp.manager.bean.InvitationCode;
import com.myErp.manager.bean.LabletType;
import com.myErp.manager.bean.RangeParameter;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserDatum;
import com.myErp.manager.bean.UserImg;
import com.myErp.manager.bean.UserInvite;
import com.myErp.manager.bean.UserLableMapping;
import com.myErp.manager.bean.UserPosition;
import com.myErp.manager.bean.UserVerifyCode;
import com.myErp.utils.Md5Util;
import com.myErp.utils.SystemConfig;

public class UserBusiness {

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
	public baseResponse initUser(UserPositionServiceImpl userPositionService, baseRequest<InitUserRequestModel> user) {

		baseResponse response = new baseResponse();
		// if (user.getUserId() > 0) {
		InitUserRequestModel initUser = user.getbody();
		if (initUser.getLon() <= 0) {
			initUser.setLon(116.403963);
		}
		if (initUser.getLat() <= 0) {
			initUser.setLat(39.915119);
		}
		UserPosition position = new UserPosition();
		position.setUserId(user.getUserId());
		position.setIsPosition(1);
		position.setLongitude(initUser.getLon());
		position.setLatitude(initUser.getLat());
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
	public VersionResponseModel GetAppVersion(AppversionServiceImpl appversionService, int deviceType,
			int versionCode) {
		VersionResponseModel versionModel = new VersionResponseModel();
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
	public PageTwoResponseModel GetPageTwo() {
		PageTwoResponseModel pageTwoModel = new PageTwoResponseModel();
		pageTwoModel.setImgUrl(SystemConfig.pageTwo);
		return pageTwoModel;
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public baseResponse userRegister(UserServiceImpl userServiceImpl,
			UserVerifyCodeServiceImpl userVerifyCodeServiceImpl, InvitationCodeServiceImpl invitationCodeServiceImpl,
			UserInviteServiceImpl userInviteServiceImpl, baseRequest<userModel> user) {
		// 检查手机号是否重复
		// 检查邀请码是否正确
		// 检查短信验证码
		userModel model = user.getbody();
		baseResponse response = new baseResponse();
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
//		UserVerifyCode userCode = userVerifyCodeServiceImpl.selectCodeByphone(model.getPhone());
//		if (userCode == null || !userCode.getVerifyCode().equals(model.getVerifyCode())) {
//			response.setCode(ResultEnum.ServiceErrorCode);
//			response.setMsg("手机验证码不正确");
//			return response;
//		}
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
			UserVerifyCodeServiceImpl userVerifyCodeServiceImpl, baseRequest<UserPwdResponseModel> user) {
		// 检查手机号是否重复
		// 检查邀请码是否正确
		// 检查短信验证码
		UserPwdResponseModel model = user.getbody();
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
	public baseResponse userLogin(UserServiceImpl userServiceImpl, baseRequest<userModel> user) {
		// 检查手机号是否重复
		// 检查邀请码是否正确
		// 检查短信验证码
		userModel model = user.getbody();
		baseResponse response = new baseResponse();
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
		return response;

	}

	/**
	 * 开启线程去添加用户坐标
	 * 
	 * @param userPositionService
	 * @param request
	 */
	public void AddUserPoint(UserPositionServiceImpl userPositionService, baseRequest request) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				UserPosition position = new UserPosition();
				position.setUserId(request.getUserId());
				position.setIsPosition(1);
				position.setLongitude(116.403963);
				position.setLatitude(39.915119);
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
	public baseResponse AddUserDatum(UserDatumServiceImpl userDatumService, baseRequest<UserDatumRequestModel> user) {
		baseResponse response = new baseResponse();
		int datumId = 0;
		UserDatumRequestModel model = user.getbody();
		UserDatum entiy = new UserDatum();
		entiy.setUserId(user.getUserId());
		entiy.setAge(model.getAge());
		entiy.setCity(model.getCity());
		entiy.setWeight(model.getWeight());
		entiy.setHeight(model.getHeight());
		entiy.setShape(model.getShape());
		entiy.setSexuat(model.getSexuat());
		entiy.setSign(model.getSign());
		// 查询用户是否存在 存在则更新 不存在添加
		UserDatum data = userDatumService.selectDatumByUserId(user.getUserId());
		if (data != null && data.getDatumId() > 0) {
			// 更新
			datumId = userDatumService.updateDatum(entiy);
		} else {
			entiy.setGender(model.getGender());
			datumId = userDatumService.insertDatum(entiy);
		}
		if (datumId <= 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("添加资料失败");
		}
		return response;

	}

	/**
	 * 获取用户资料
	 * 
	 * @param userDatumService
	 * @param user
	 * @return
	 */
	public baseResponse<UserDatumRequestModel> GetUserDatum(UserDatumServiceImpl userDatumService,
			baseRequest<UserDatumRequestModel> user) {
		baseResponse<UserDatumRequestModel> response = new baseResponse<UserDatumRequestModel>();
		UserDatum data = userDatumService.selectDatumByUserId(user.getUserId());
		if (data != null) {
			UserDatumRequestModel model = new UserDatumRequestModel();
			model.setAge(data.getAge());
			model.setGender(data.getGender());
			model.setCity(data.getCity());
			model.setWeight(data.getWeight());
			model.setHeight(data.getHeight());
			model.setShape(data.getShape());
			model.setSexuat(data.getSexuat());
			model.setSign(data.getSign());
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
			LableResponseModel lableModel = new LableResponseModel();
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
	public void UpdateUserBrowse(UserServiceImpl userServiceImpl, int userId) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				userServiceImpl.updateBrowseNumber(userId);
			}
		});
		t.start();
	}

	/**
	 * 用户个人主页详情
	 * @param userServiceImpl
	 * @param userImgServiceImpl
	 * @param userLableMappingServiceImpl
	 * @param request
	 * @return
	 */
	public baseResponse<DetailsResponseModel> GetUserDetails(UserServiceImpl userServiceImpl,
			UserImgServiceImpl userImgServiceImpl, UserLableMappingServiceImpl userLableMappingServiceImpl,
			baseRequest<DetailsRequestModel> request) {

		baseResponse<DetailsResponseModel> response = new baseResponse<DetailsResponseModel>();

		DetailsResponseModel details = new DetailsResponseModel();

		DetailsRequestModel requestModel = request.getbody();
		//
		RangeParameter rangeParameter = new RangeParameter();
		rangeParameter.setUserId(requestModel.getToUserId());
		// 如果经纬度小于等于0 证明当前位置不可访问 默认天安门
		if (requestModel.getLat() <= 0 || requestModel.getLon() <= 0) {
			requestModel.setLat(ResultEnum.defaultLat);
			requestModel.setLon(ResultEnum.defaultLon);
		}
		rangeParameter.setLat(requestModel.getLat());
		rangeParameter.setLon(requestModel.getLon());
		List<User> userDatas = userServiceImpl.getUserDetails(rangeParameter);
		if (userDatas != null && userDatas.size() > 0) {
			User userData = userDatas.get(0);
			UserDatum datum = userData.getDatum();
			details.setHeadImage(userData.getHeadImage());
			details.setId(userData.getId());
			details.setNickName(userData.getNickName());
			details.setGender(datum.getGender());
			details.setAge(datum.getAge());
			details.setCity(datum.getCity());
			details.setRange(ResponseUtils.GetRange(datum.getRangeM()));
			details.setSign(datum.getSign());
			details.setWeight(datum.getWeight());
			details.setHeight(datum.getHeight());
			details.setShape(datum.getShape());
			details.setSexuat(datum.getSexuat());
		}
		// 图片
		List<UserImg> userImgs = userImgServiceImpl.selectImgtByUserId(requestModel.getToUserId());
		if (userImgs != null && userImgs.size() > 0) {
			for (UserImg userImg : userImgs) {
				DetailsImgResponseModel img = new DetailsImgResponseModel();
				img.setImg(userImg.getImagePath());
				details.getImgs().add(img);
			}

		}
		// 标签
		UserLableMapping mapping = new UserLableMapping();
		mapping.setUserId(requestModel.getToUserId());
		List<UserLableMapping> userLableData = userLableMappingServiceImpl.selectlabletByUserId(mapping);
		for (UserLableMapping userLableMapping : userLableData) {
			DetailsLableResponseModel lable = new DetailsLableResponseModel();
			lable.setLableName(userLableMapping.getLabletTypes().getLableName());
			details.getLables().add(lable);
		}
		response.setData(details);

		UserBusiness.getInstance().UpdateUserBrowse(userServiceImpl, requestModel.getToUserId());
		return response;
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
