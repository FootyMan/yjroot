package com.api.controller;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.api.business.HomeBusiness;
import com.api.request.*;
import com.api.response.RangeResponse;
import com.api.response.BaseResponse;
import com.api.utils.decrypt.ResponseEncryptBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/home", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "首页接口")
public class HomeController {

	@Autowired
	private HomeBusiness homeBusiness;

	/**
	 * 首页用户
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-user", value = "首页用户", notes = "首页用户")
	public BaseResponse<HomeObject> HomeUser(@ApiParam(value = "输入") @RequestBody baseRequest<HomeRequest> request)
			throws Exception {

		return homeBusiness.GetHomeList(request);
	}

	/**
	 * 附近的人
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/range", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-range", value = "附近的人 如果不让访问当前位置 经纬度不需要传、默认天安门", notes = "附近的人")
	public BaseResponse<RangeResponse> RangeUser(@ApiParam(value = "输入") @RequestBody baseRequest<RangeRequest> request)
			throws Exception {

		return homeBusiness.GetRangeList(request);
	}

}
