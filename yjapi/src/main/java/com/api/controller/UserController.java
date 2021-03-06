package com.api.controller;

import java.io.UnsupportedEncodingException;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.api.business.BusinessUtils;
import com.api.business.FileBusiness;
import com.api.business.UserBusiness;
import com.api.request.*;
import com.api.utils.PhoneMessageSend;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.api.utils.decrypt.ResponseEncryptBody;
import com.service.bean.User;
import com.service.bean.UserVerifyCode;
import com.service.utils.StringUtils;
import com.service.utils.SystemConfig;
import com.service.utils.ValidateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import com.api.response.*;

@Controller
@RequestMapping(value = "/user", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "用户接口")
public class UserController {
	@Autowired
	private UserBusiness userBusiness;
	@Autowired
	private FileBusiness fileBusiness;

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
	public BaseResponse<InitResponse> userRegister(@ApiParam(value = "输入") @RequestBody baseRequest<userModel> request)
			throws Exception {
		BaseResponse<InitResponse> response = userBusiness.userRegister(request);
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
	public BaseResponse<InitResponse> userLogin(@ApiParam(value = "输入") @RequestBody baseRequest<userModel> user)
			throws Exception {
		BaseResponse<InitResponse> response = userBusiness.userLogin(user);
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
	public BaseResponse<?> UpdateUserPwd(@ApiParam(value = "输入") @RequestBody baseRequest<UserPwdResponse> user)
			throws Exception {
		BaseResponse<?> response = userBusiness.UpdateUserPwd(user);
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
	public BaseResponse<?> getMsgCode(@ApiParam(value = "输入") @RequestBody baseRequest<PhoneMsgRequest> request)
			throws Exception {
		BaseResponse<?> response = new BaseResponse<Object>();
		
		
		//User userData = userServiceImpl.selectUserByUserId(request.getUserId());
		PhoneMsgRequest body=request.getbody();
		if (body==null || StringUtils.isEmpty(body.getPhone())) {
			response.setCode(ResultEnum.ColmunErrorCode);
			response.setMsg("手机号为空");
			return response;
		}
		
		// 验证码 如果是测试默认1234
		String code = "1234";
		if (!"qa".equals(SystemConfig.EnvIdentity)) {
			code = ValidateUtil.GetRandom();
		}
		PhoneMessageSend send = new PhoneMessageSend();
		boolean isflag = false;// 发送是否成功

		Map<String, String> map = userBusiness.GetMessage(request, response, code);
		if (map != null && !"qa".equals(SystemConfig.EnvIdentity)) {
			isflag = send.SendPhooneMsg(map.get("phone"), map.get("msg"));
			if (!isflag) {

				response.setCode(ResultEnum.ServiceErrorCode);
				response.setMsg("短信通道失败");
				return response;
			}
		} else {
			if (!StringUtils.isEmpty(response.getMsg())) {

				return response;
			} else if (map == null) {
				response.setCode(ResultEnum.ServiceErrorCode);
				response.setMsg("参数不符");
				return response;
			}
		}
		UserVerifyCode verifyCode = new UserVerifyCode();
		verifyCode.setPhone(request.getbody().getPhone());
		verifyCode.setVerifyCode(code);
		userBusiness.AddUserVerifyCode(verifyCode);
		return response;
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
	public BaseResponse<InitResponse> AddUserDatum(
			@ApiParam(value = "输入") @RequestBody baseRequest<UserDatumRequest> user) throws Exception {
		BaseResponse<InitResponse> response = userBusiness.AddUserDatum(user);
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
	public BaseResponse<UserDatumRequest> GetUserDatum(@ApiParam(value = "输入") @RequestBody baseRequest user)
			throws Exception {
		BaseResponse<UserDatumRequest> response = userBusiness.GetUserDatum(user);
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
	public BaseResponse<MyLableResponse> AddUserLable(
			@ApiParam(value = "输入") @RequestBody baseRequest<LableRequestData> request) {
		return userBusiness.AddUserLable(request);
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
	public BaseResponse<MyLableResponse> GetUserLableByUserID(
			@ApiParam(value = "输入") @RequestBody baseRequest<?> request) throws Exception {
		return userBusiness.GetUserLableByUserID(request);
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
	public BaseResponse<DetailsResponse> GetUserDetails(
			@ApiParam(value = "输入") @RequestBody baseRequest<DetailsRequest> request) {
		return userBusiness.GetUserDetails(request);
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
	public BaseResponse<UserLikeResponse> UserLike(
			@ApiParam(value = "输入") @RequestBody baseRequest<UserLikeRequest> request) {
		return userBusiness.UserLike(request);
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
	public BaseResponse<LikeListResponse> UserLikeList(@ApiParam(value = "输入") @RequestBody baseRequest<LikeListRequest> request) {
		return userBusiness.UserLikeList(request);
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
	public BaseResponse<?> GetUserBrowse(@ApiParam(value = "输入") @RequestBody baseRequest<BrowseRequest> request) {
		return userBusiness.GetUserBrowse(request);
	}
	/**
	 * 删除访客
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/rmBrowse", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-details", value = "删除访客", notes = " 删除成功不返回任何数据 客户端直接删除即可 ")
	public BaseResponse<?> RemoveBrowse(@ApiParam(value = "输入") @RequestBody baseRequest<RemoveBrowseRequest> request) {
		return userBusiness.RemoveBrowse(request);
	}

	/**
	 * 我的图片
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/myImg", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-details", value = "我的图片", notes = "我的图片")
	public BaseResponse<MyImageResponse> GetMyImg(@ApiParam(value = "输入") @RequestBody baseRequest<?> request) {
		return userBusiness.GetUserImage(request);
	}

	/**
	 * 删除图片
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/delImg", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-details", value = "图片删除", notes = "图片删除")
	public BaseResponse<?> RemonveImage(@ApiParam(value = "输入") @RequestBody baseRequest<RemoveImgRequest> request) {
		BaseResponse<?> response = userBusiness.RemonveImageByImageId(request);
		// 删除磁盘
		//fileBusiness.RemoveFile(request.getbody().getImgUrl());
		return response;
	}
	/**
	 * 举报用户
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/inform", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-details", value = "举报用户", notes = "举报用户")
	public BaseResponse<?> UserInform (@ApiParam(value = "输入") @RequestBody baseRequest<InformRequest> request) {
		BaseResponse<?> response = userBusiness.UserInform(request);
		return response;
	}

}
