package com.api.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.api.business.PayNotifBusiness;
import com.api.business.UserBusiness;
import com.api.request.HomeObject;
import com.api.request.HomeRequest;
import com.api.request.IapRequest;
import com.api.request.baseRequest;
import com.api.response.BaseResponse;
import com.api.response.IapInapp;
import com.api.response.IapReceipt;
import com.api.response.IapReceiptResult;
import com.api.response.IapResponse;
import com.api.utils.decrypt.ResponseEncryptBody;
import com.google.gson.Gson;
import com.service.api.impl.OrderServiceImpl;
import com.service.api.impl.ProductServiceImpl;
import com.service.bean.Order;
import com.service.bean.Product;
import com.service.utils.StringUtils;
import com.service.utils.SystemConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/iap", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "苹果内购支付验证")
public class IapController {

	private static Logger logger = Logger.getLogger(IapController.class);
	@Autowired
	private PayNotifBusiness payNotifBusiness;
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	/**
	 * 重写X509TrustManager
	 */
	private static TrustManager myX509TrustManager = new X509TrustManager() {

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

		}

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

		}
	};

	/**
	 * 首页用户
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/verifyIap", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-verifyIap", value = "苹果内购验证", notes = "苹果内购验证")
	public BaseResponse<IapResponse> VerifyIap(@ApiParam(value = "输入") @RequestBody baseRequest<IapRequest> request)
			throws Exception {
		BaseResponse<IapResponse> response = new BaseResponse<IapResponse>();
		IapResponse iapResponse = new IapResponse();
		iapResponse.setIsVerify(1);
		IapRequest body = request.getbody();
		if (StringUtils.isEmpty(body.getReceipt()) || body.getUserId()==0) {
			return null;
		}
		logger.info("苹果内购票据参数:" + body.getReceipt());
		String url =SystemConfig.CertificateUrl;
		String jsonStr = sendHttpsCoon(url, body.getReceipt());
		logger.info("苹果内购验证返回:" + jsonStr);
		// 解析
		Gson gson = new Gson();
		IapReceiptResult result = gson.fromJson(jsonStr, IapReceiptResult.class);
		if (result.getStatus() == 0) {

			IapReceipt receipt = result.getReceipt();
			//此处遍历苹果返回in_app数组傻X苹果
			for (IapInapp inapp : result.getReceipt().getIn_app()) {
				//先获取产品
				//根据用户id加订单 是否存在这笔订单
				int productId= Integer.parseInt(inapp.getProduct_id().replace("com.zhzy.yujian.", ""));
				
				//查询订单是否存在
				Order orderData = orderServiceImpl.selectOrderByNumber(inapp.getTransaction_id());
				if (orderData==null) {
					
					Product product = productServiceImpl.selectProductById(productId);
					// 生成订单
					Order order = new Order();
					order.setOrderNumber(inapp.getTransaction_id());
					order.setUserId(body.getUserId());
					order.setProductId(product.getProductId());
					order.setOrderSource(1);//ios
					order.setOrderPrice(product.getPrice());
					order.setOrderSource(request.getDeviceType());
					order.setPayType(3);//内购
					orderServiceImpl.insertOrder(order);
					payNotifBusiness.Pay(inapp.getTransaction_id());
				}
			}
			
		} else {
			iapResponse.setIsVerify(0);
		}

		response.setData(iapResponse);
		return response;
		// return homeBusiness.GetHomeList(request);
	}

	/**
	 * 发送请求
	 * 
	 * @param url
	 * @param strings
	 * @return
	 */
	private String sendHttpsCoon(String url, String code) {
		if (url.isEmpty()) {
			return null;
		}
		try {
			// 设置SSLContext
			SSLContext ssl = SSLContext.getInstance("SSL");
			ssl.init(null, new TrustManager[] { myX509TrustManager }, null);

			// 打开连接
			HttpsURLConnection conn = (HttpsURLConnection) new URL(url).openConnection();
			// 设置套接工厂
			conn.setSSLSocketFactory(ssl.getSocketFactory());
			// 加入数据
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-type", "application/json");

			JSONObject obj = new JSONObject();
			obj.put("receipt-data", code);

			BufferedOutputStream buffOutStr = new BufferedOutputStream(conn.getOutputStream());
			buffOutStr.write(obj.toString().getBytes());
			buffOutStr.flush();
			buffOutStr.close();

			// 获取输入流
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();

		} catch (Exception e) {
			return null;
		}
	}

}
