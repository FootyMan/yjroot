package com.api.controller;

import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.api.business.UserBusiness;
import com.api.request.*;
import com.api.utils.PhoneMessageSend;
import com.api.utils.ResultEnum;
import com.api.utils.decrypt.ResponseEncryptBody;
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
import com.myErp.manager.bean.LabletType;
import com.myErp.manager.bean.UserLableMapping;
import com.myErp.manager.bean.UserVerifyCode;
import com.myErp.utils.StringUtils;
import com.myErp.utils.SystemConfig;
import com.myErp.utils.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import com.api.response.*;

@Controller
@RequestMapping(value = "/user", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "用户接口")
public class userController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserVerifyCodeServiceImpl userVerifyCodeServiceImpl;
	@Autowired
	private UserPositionServiceImpl UserPositionService;
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
	private ProvinceServiceImpl provinceServiceImpl;

	/**
	 * 用户注册接口 注册成功返回用户所有信息
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/registe", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-registe", value = "用户注册接口", notes = "用户注册接口")
	public baseResponse<InitResponse> userRegister(@ApiParam(value = "输入") @RequestBody baseRequest<userModel> user)
			throws Exception {
		baseResponse<InitResponse> response = UserBusiness.getInstance().userRegister(userServiceImpl,
				userVerifyCodeServiceImpl, invitationCodeServiceImpl, userInviteServiceImpl, user);
		UserBusiness.getInstance().AddUserPoint(UserPositionService, user);
		// InitUserResponse initUser =
		// UserBusiness.getInstance().InitUserData(userServiceImpl,
		// user.getUserId());
		// response.setData(initUser);
		return response;
	}

	/**
	 * 用户登录接口
	 * 
	 * @param input
	 * @return
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-registe", value = "用户登录接口", notes = "用户登录接口")
	public baseResponse<InitResponse> userLogin(@ApiParam(value = "输入") @RequestBody baseRequest<userModel> user)
			throws Exception {
		baseResponse<InitResponse> response = UserBusiness.getInstance().userLogin(userServiceImpl, user);
		return response;
	}

	/**
	 * 修改密码
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/pwd", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-pwd", value = "修改密码", notes = "修改密码")
	public baseResponse<?> UpdateUserPwd(@ApiParam(value = "输入") @RequestBody baseRequest<UserPwdResponse> user)
			throws Exception {
		baseResponse<?> response = UserBusiness.getInstance().UpdateUserPwd(userServiceImpl, userVerifyCodeServiceImpl,
				user);
		return response;
	}

	/**
	 * 获取短信验证码
	 * 
	 * @param input
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/getMsgCode", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-getMsgCode", value = "获取短信验证码", notes = "获取短信验证码")
	public baseResponse<?> getMsgCode(@ApiParam(value = "输入") @RequestBody baseRequest<PhoneMsgRequest> request)
			throws Exception {
		baseResponse<?> output = new baseResponse<Object>();
		// 验证码 如果是测试默认1234
		String code = "1234";
		if (!SystemConfig.isQAplatform) {
			code = ValidateUtil.GetRandom();
		}
		PhoneMessageSend send = new PhoneMessageSend();
		boolean isflag = false;// 发送是否成功

		Map<String, String> map = UserBusiness.getInstance().GetMessage(userServiceImpl, request, output, code);
		if (map != null && !SystemConfig.isQAplatform) {
			isflag = send.SendPhooneMsg(map.get("phone"), map.get("msg"));
			if (!isflag) {

				output.setCode(ResultEnum.ServiceErrorCode);
				output.setMsg("短信通道失败");
				return output;
			}
		} else {
			if (!StringUtils.isEmpty(output.getMsg())) {

				return output;
			} else if (map == null) {
				output.setCode(ResultEnum.ServiceErrorCode);
				output.setMsg("参数不符");
				return output;
			}
		}
		UserVerifyCode verifyCode = new UserVerifyCode();
		verifyCode.setPhone(request.getbody().getPhone());
		verifyCode.setVerifyCode(code);
		userVerifyCodeServiceImpl.insertUserVerifyCode(verifyCode);
		return output;
	}

	/**
	 * 添加用户基本资料
	 * 
	 * @param input
	 * @return
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/editDatum", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-addDatum", value = "添加用户资料和修改用户资料）", notes = "添加用户资料和修改用户资料")
	public baseResponse<?> AddUserDatum(@ApiParam(value = "输入") @RequestBody baseRequest<UserDatumRequest> user)
			throws Exception {
		baseResponse<?> response = UserBusiness.getInstance().AddUserDatum(userServiceImpl,userDatumService, user);
		return response;
	}

	/**
	 * 获取用户基本资料
	 * 
	 * @param input
	 * @return
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/getDatum", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-getDatum", value = "获取用户资料", notes = "获取用户资料")
	public baseResponse<UserDatumRequest> GetUserDatum(@ApiParam(value = "输入") @RequestBody baseRequest user)
			throws Exception {
		baseResponse<UserDatumRequest> response = UserBusiness.getInstance().GetUserDatum(userServiceImpl, user);
		// UserBusiness.getInstance().test();
		return response;
	}

	/**
	 * 添加用户标签
	 * 
	 * @param input
	 * @return
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/addLable", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-addLable", value = "添加用户标签 添加成功立即返回添加后的数据", notes = "添加用户标签")
	public baseResponse<LableResponseData> AddUserLable(
			@ApiParam(value = "输入") @RequestBody baseRequest<LableRequestData> request) {
		baseResponse<LableResponseData> response = new baseResponse<LableResponseData>();

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
	 * @param input
	 * @return
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/getUserlable", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-addLable", value = "根据用户ID获取他的所有标签", notes = "根据用户ID获取他的所有标签")
	public baseResponse<InitResponseAppData> GetUserLableByUserID(
			@ApiParam(value = "输入") @RequestBody baseRequest request) throws Exception {
		baseResponse<InitResponseAppData> response = new baseResponse<InitResponseAppData>();
		UserLableMapping mapping = new UserLableMapping();
		mapping.setUserId(request.getUserId());
		List<UserLableMapping> userLableData = userLableMappingServiceImpl.selectlabletByUserId(mapping);
		if (userLableData != null && userLableData.size() > 0) {
			List<LabletType> types = new ArrayList<LabletType>();
			for (UserLableMapping userLableMapping : userLableData) {
				types.add(userLableMapping.getLabletTypes());
			}
			response.setData(UserBusiness.getInstance().LableEntityToModel(types));
		}
		return response;
	}

	/**
	 * 个人主页
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-details", value = "个人主页", notes = "个人主页  喜欢不喜欢是isLike ")
	public baseResponse<DetailsResponse> GetUserDetails(
			@ApiParam(value = "输入") @RequestBody baseRequest<DetailsRequest> request) {
		return UserBusiness.getInstance().GetUserDetails(userServiceImpl, userImgServiceImpl,
				userLableMappingServiceImpl, userlikeServiceImpl, userBrowseServiceImpl, request);
	}

	/**
	 * 用户喜欢
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-details", value = "用户喜欢", notes = "用户喜欢")
	public baseResponse<UserLikeResponse> UserLike(
			@ApiParam(value = "输入") @RequestBody baseRequest<UserLikeRequest> request) {

		return UserBusiness.getInstance().UserLike(userlikeServiceImpl, request);
	}

	/**
	 * 喜欢列表
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/likeList", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-details", value = "喜欢列表", notes = "喜欢列表")
	public baseResponse<List<HomeResponse>> UserLikeList(@ApiParam(value = "输入") @RequestBody baseRequest<?> request) {
		return UserBusiness.getInstance().UserLikeList(userServiceImpl, request);
	}

	/**
	 * 最近访客数量
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/browse", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-details", value = "最近访客", notes = "最近访客 "
			+ "	如果传入type=1 获取数量 返回body里只有browseNumber	" + "	如果传入type=2获取访问列表 传入pageIndex 返回列表比首页多一个访问时间browseData")
	public baseResponse<?> GetUserBrowse(@ApiParam(value = "输入") @RequestBody baseRequest<BrowseRequest> request) {
		return UserBusiness.getInstance().GetUserBrowse(userBrowseServiceImpl, userServiceImpl, request);
	}
}
