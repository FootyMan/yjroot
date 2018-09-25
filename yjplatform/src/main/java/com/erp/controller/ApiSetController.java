package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.redis.IosPayTypeManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/set")
@Controller
@Api(tags = "接口系统设置")
public class ApiSetController extends BaseController {

	@RequestMapping(value = "/setPayType", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(nickname = "swagger-user", value = "是否走内购非0为真 0不走 1走", notes = "是否走内购非0为真 0不走 1走")
	public String SetIosPayType(int isIap) {
		IosPayTypeManager payManger=new IosPayTypeManager();
		return payManger.SetPayType(isIap);

	}
	
	@RequestMapping(value = "/getPayType", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(nickname = "swagger-user", value = "是否走内购非0为真 0不走 1走", notes = "是否走内购非0为真 0不走 1走")
	public int GetIosPayType() {
		IosPayTypeManager payManger=new IosPayTypeManager();
		return payManger.GetPayType();

	}
}
