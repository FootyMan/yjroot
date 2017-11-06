package com.service.alipay.sdk;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

public class AlipayPayManager {
	

	public String GetOrderString(String orderNumber,String productdesc,String amount)
	{
		// 实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset,
				AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		// 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		// SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setOutTradeNo(orderNumber);
		model.setSubject(productdesc);
		model.setBody("北京众和致远科技有限公司");
		model.setTotalAmount(amount);
		model.setTimeoutExpress("30m");
		model.setProductCode("QUICK_MSECURITY_PAY");
		request.setBizModel(model);
		request.setNotifyUrl(AlipayConfig.notify_url);
		// 这里和普通的接口调用不同，使用的是sdkExecute
		try {
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			if (response.isSuccess()) {
				System.out.println("调用成功");
				System.out.println(response.getBody());
				return response.getBody();
			} else {
				System.out.println("调用失败");
				System.out.println(response.getBody());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());// 就是orderString
		}
		return "";
	}
}
