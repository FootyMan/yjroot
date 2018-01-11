package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.api.business.InitBusiness;

import com.api.request.*;
import com.api.response.*;
import com.api.utils.ResultEnum;
import com.api.utils.decrypt.ResponseEncryptBody;
import com.service.utils.StringUtils;
import com.service.utils.SystemConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/init", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "初始化接口")
public class InitController {

	@Autowired
	InitBusiness initBusiness;
	// @Autowired
	// private InvitationCodeServiceImpl invitationCodeServiceImpl;

	/**
	 * 初始化用户信息 1、登录和注册之后 必须调用此接口（方便获取用户位置） 2、如果已登录 打开APP先调用此接口 传入经纬度
	 * 3、此接口返回用户偏好设置 4返回用户提示更新 5二次启动页
	 * 
	 * @param input
	 * @return
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/initUser", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-initUser", value = "初始化用户"
			+ "1、登录和注册之后 必须调用此接口（方便获取用户位置）2、如果已登录 打开APP先调用此接口 传入经纬度 3、此接口返回用户偏好设置", notes = "初始化用户")
	public BaseResponse<InitResponse> initUser(// InitUserRequest
			@ApiParam(value = "输入") @RequestBody baseRequest<?> request) throws Exception {

		BaseResponse<InitResponse> response = new BaseResponse<InitResponse>();
		InitResponse initUser = initBusiness.InitUserData(request.getUserId());
		response.setData(initUser);
		//更新坐标
		initBusiness.initUser(request);
		//判断没有头像的提示上传头像并且注册资料完善的
		if (initUser.getUser().isFull()&& StringUtils.isEmpty(initUser.getUser().getHeadImage().replace(SystemConfig.ImgurlPrefix, ""))) {
			response.setMsg("没头像还想约会?");
			response.setCode(ResultEnum.ServiceErrorCode);
		}
		return response;
	}

	/**
	 * 初始化app填充的数据 如城市、我的标签 角色
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/appData", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-user", value = "初始化app填充的数据 如城市、我的标签 角色4返回用户提示更新 5二次启动页", notes = "初始化app填充的数据")
	public BaseResponse<InitResponseAppData> InitAppData(@ApiParam(value = "输入") @RequestBody baseRequest<?> request)
			throws Exception {
		//更新坐标
		initBusiness.initUser(request);
		return initBusiness.InitAppData(request);
	}

	/**
	 * 初始化redis
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/redis", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-user", value = "服务端自用", notes = "服务端自用")
	public BaseResponse<?> InitRedis(@ApiParam(value = "输入") @RequestBody baseRequest<InitRedisRequest> request)
			throws Exception {

		return initBusiness.InitRedis(request);
	}

}
