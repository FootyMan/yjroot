package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.api.business.OrderBusiness;
import com.api.request.OrderRequest;
import com.api.request.baseRequest;
import com.api.response.BaseResponse;
import com.api.response.ProductsResponse;
import com.api.utils.decrypt.ResponseEncryptBody;
import com.service.api.impl.ProductServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/order", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "订单接口")
public class OrderController {
	@Autowired
	private OrderBusiness orderBusiness;

	/**
	 * 订单支付
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-user", value = "订单支付", notes = "支付接口返回匿名类型" + "如果是支付宝body里面会有一个alipayString"
			+ "	如果是是微信body包含appid=appid" + "	partnerid=商户Id" + "	prepayid=预支付ID" + "	trade_type=类型" + "	sign=签名"
			+ "	timeStamp=时间戳" + "	nonce_str=随机数")
	public synchronized BaseResponse<?> PayOrder(@ApiParam(value = "输入") @RequestBody baseRequest<OrderRequest> request)
			throws Exception {

		return orderBusiness.PayOrder(request);
	}

	/**
	 * 获取购买产品
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-user", value = "获取购买产品", notes = "获取购买产品")
	public BaseResponse<ProductsResponse> GetProduct(@ApiParam(value = "输入") @RequestBody baseRequest<?> request) {
		return orderBusiness.GetProducts(request);
	}
}
