package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.api.business.ProfitBusiness;

import com.api.request.ProfitAccountRequest;
import com.api.request.ProfitRecordRequest;
import com.api.request.ProfitWithDrawRequest;
import com.api.request.baseRequest;

import com.api.response.ProfitMyResponse;

import com.api.response.BaseResponse;

import com.api.utils.decrypt.ResponseEncryptBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/profit", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "用户收益接口")
public class ProfitController {

	@Autowired
	private ProfitBusiness profitBusiness;

	/**
	 * 绑定支付宝账号
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-registe", value = "绑定支付宝账号", notes = "绑定支付宝账号")
	public BaseResponse<?> BindAccount(@ApiParam(value = "输入") @RequestBody baseRequest<ProfitAccountRequest> request)
			throws Exception {

		return profitBusiness.BindAccount(request);
	}

	/**
	 * 我的收益 我的收益++提现页面初始化的账号+可提现金额+每日提现上限
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/myProfit", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-registe", value = "我的收益++提现页面初始化的账号+可提现金额+每日提现上限", notes = "我的收益++提现页面初始化的账号+可提现金额+每日提现上限")
	public BaseResponse<ProfitMyResponse> MyProfit(@ApiParam(value = "输入") @RequestBody baseRequest<?> request)
			throws Exception {

		return profitBusiness.GetMyProfit(request);
	}

	/**
	 * 收益和提现记录
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-registe", value = "收益和提现记录 注意返回的data里是集合 字段类型全部String"
			+ "收益记录返回date=日期	user=用户	type=会员类型	money=佣金"
			+ "		提现记录date=日期	money=提现金额		state=结算状态", notes = "收益和提现记录")
	public BaseResponse<?> ProfitRecord(@ApiParam(value = "输入") @RequestBody baseRequest<ProfitRecordRequest> request)
			throws Exception {

		return profitBusiness.ProfitRecord(request);
	}

	/**
	 * 提现
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/wd", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-registe", value = "提现", notes = "提现")
	public BaseResponse<?> UserWithdrawals(
			@ApiParam(value = "输入") @RequestBody baseRequest<ProfitWithDrawRequest> request) throws Exception {
		return profitBusiness.UserWithdrawals(request);
	}
}
