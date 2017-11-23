package com.api.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.api.business.PayNotifBusiness;
import com.api.wxpay.sdk.WXPayUtil;

@Controller
@RequestMapping(value = "/payNotif")
// @Api(tags = "支付通知接口")
public class PayNotifController {

	private static Logger logger = Logger.getLogger(PayNotifController.class);
	@Autowired
	private PayNotifBusiness payNotifBusiness;

	/**
	 * 微信支付后台通知
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wxNotif", method = RequestMethod.POST)
	public void WxNotif(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setCharacterEncoding("utf-8");
		System.out.println("微信支付回调");
		PrintWriter writer = response.getWriter();
		InputStream inStream = request.getInputStream();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		String result = new String(outSteam.toByteArray(), "utf-8");
		logger.info("微信支付通知结果：" + result);
		Map<String, String> map = WXPayUtil.xmlToMap(result);
		if (map.get("return_code").equals("SUCCESS")) {
			logger.info("微信支付成功 订单号：" + map.get("out_trade_no"));
			payNotifBusiness.Pay(map.get("out_trade_no"));
			// String notifyStr = WXPayUtil.setXML("SUCCESS", "");
			// 更新订单状态
			// 更新用户级别
			// 增加用户充值记录
			// 更新用户账户给邀请人返还20%钱 用于提现
			writer.write("SUCCESS");
			writer.flush();
		}

	}

	/**
	 * 支付宝后台通知
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/aliNotif")
	public void AliNotif(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		Map requestParams = request.getParameterMap();
		logger.info("支付宝回调参数：" + requestParams.toString());
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		if (requestParams.size() > 0) {
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"),
				// "utf-8");
				params.put(name, valueStr);
			}
			if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
				// 付款金额
				String amount = params.get("buyer_pay_amount");
				// 商户订单号
				String out_trade_no = params.get("out_trade_no");
				// 支付宝交易号
				logger.info("支付宝回调参数订单号：" + out_trade_no);
				String trade_no = params.get("trade_no");
				payNotifBusiness.Pay(out_trade_no);
				response.getWriter().write("SUCCESS");
			} else {
				response.getWriter().write("fail");
			}
		} else {
			response.getWriter().write("无通知参数");
		}

		// String notifyStr = WXPayUtil.setXML("SUCCESS", "");
		// 更新订单状态
		// 更新用户级别
		// 增加用户充值记录
		// 更新用户账户给邀请人返还20%钱 用于提现

	}


}
