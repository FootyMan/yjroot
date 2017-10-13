package com.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.api.alipay.sdk.AlipayPayManager;
import com.api.request.HomeObject;
import com.api.request.HomeRequest;
import com.api.request.OrderRequest;
import com.api.request.baseRequest;
import com.api.response.AlipayResponse;
import com.api.response.HomeResponse;
import com.api.response.WxResponse;
import com.api.response.baseResponse;
import com.api.utils.decrypt.ResponseEncryptBody;
import com.myErp.impl.OrderServiceImpl;
import com.myErp.impl.ProductServiceImpl;
import com.myErp.manager.bean.AppHomePagePaging;
import com.myErp.manager.bean.Order;
import com.myErp.manager.bean.Product;
import com.myErp.manager.bean.User;
import com.myErp.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/order", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "订单接口")
public class OrderController {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;

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
	public synchronized baseResponse PayOrder(@ApiParam(value = "输入") @RequestBody baseRequest<OrderRequest> request)
			throws Exception {
		OrderRequest orderModel = request.getbody();
		Product product = productServiceImpl.selectProductById(orderModel.getProductId());
		if (product != null && product.getProductId() > 0) {
			// 生成订单
			Order order = new Order();
			order.setOrderNumber(StringUtils.GetOrderNumber(request.getUserId(), product.getProductId()));
			order.setUserId(request.getUserId());
			order.setProductId(product.getProductId());
			order.setOrderSource(1);
			order.setOrderPrice(product.getPrice());
			order.setOrderSource(request.getDeviceType());
			order.setPayType(orderModel.getPayType());
			orderServiceImpl.insertOrder(order);
		}
		if (orderModel.getPayType() == 1) {
			baseResponse<WxResponse> wxResponse = new baseResponse<WxResponse>();
			WxResponse wx = new WxResponse();
			wx.setAppid("Appid");
			wx.setPartnerid("Partnerid");
			wx.setPrepayid("prepayid");
			wx.setTrade_type("trade_type");
			wx.setSign("sign");
			wx.setTimeStamp("timeStamp");
			wx.setNonce_str("nonce_str");
			wxResponse.setData(wx);
			return wxResponse;
		}
		// 支付宝
		else if (orderModel.getPayType() == 2) {
			baseResponse<AlipayResponse> aliPayResponse = new baseResponse<AlipayResponse>();

			AlipayResponse alipayResponseModel = new AlipayResponse();
			AlipayPayManager alipayManager = new AlipayPayManager();
			// 获取订单号
			String orderNumber = StringUtils.GetOrderNumber(request.getUserId(), orderModel.getProductId());
			// 获取支付宝订单字符串
			String orderString = alipayManager.GetOrderString(orderNumber, product.getProductDesc(),
					String.valueOf(product.getPrice()));
			alipayResponseModel.setAlipayString(orderString);
			aliPayResponse.setData(alipayResponseModel);
			return aliPayResponse;
		}
		return null;
	}
}
