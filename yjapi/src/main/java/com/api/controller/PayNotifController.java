package com.api.controller;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.response.baseResponse;
import com.api.utils.ExceptionHandler;
import com.api.wxpay.sdk.WXPayUtil;
import com.myErp.enums.FinancialOperateStatus;
import com.myErp.enums.FinancialType;
import com.myErp.enums.OrderState;
import com.myErp.enums.ProductLevel;
import com.myErp.enums.RechargeValid;
import com.myErp.impl.OrderServiceImpl;
import com.myErp.impl.UserFinancialDetailServiceImpl;
import com.myErp.impl.UserFinancialServiceImpl;
import com.myErp.impl.UserRechargeServiceImpl;
import com.myErp.impl.UserServiceImpl;
import com.myErp.manager.bean.Order;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserFinancial;
import com.myErp.manager.bean.UserFinancialDetail;
import com.myErp.manager.bean.UserRecharge;
import com.myErp.utils.DateUtil;
import com.myErp.utils.SystemConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/payNotif")
// @Api(tags = "支付通知接口")
public class PayNotifController {

	private static Logger logger = Logger.getLogger(PayNotifController.class);
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserRechargeServiceImpl userRechargeServiceImpl;
	@Autowired
	private UserFinancialServiceImpl userFinancialServiceImpl;
	@Autowired
	private UserFinancialDetailServiceImpl userFinancialDetailServiceImpl;

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
		System.out.println("微信支付通知结果：" + result);
		Map<String, String> map = WXPayUtil.xmlToMap(result);
		if (map.get("return_code").equals("SUCCESS")) {
			System.out.println("微信支付成功 订单号：" + map.get("out_trade_no"));
			Pay(map.get("out_trade_no"));
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
		if (requestParams.size()>0) {
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			if ("TRADE_SUCCESS".equals(params.get("trade_status"))) {
				// 付款金额
				String amount = params.get("buyer_pay_amount");
				// 商户订单号
				String out_trade_no = params.get("out_trade_no");
				// 支付宝交易号
				String trade_no = params.get("trade_no");
				Pay(out_trade_no);
				response.getWriter().write("SUCCESS");
			}
			else
			{
				response.getWriter().write("fail");
			}
		}
		else
		{
			response.getWriter().write("无通知参数");
		}
		
		// String notifyStr = WXPayUtil.setXML("SUCCESS", "");
		// 更新订单状态
		// 更新用户级别
		// 增加用户充值记录
		// 更新用户账户给邀请人返还20%钱 用于提现

		

	}

	public void Pay(String orderNumber) {
		Order orderData = orderServiceImpl.selectOrderByNumber(orderNumber);
		if (orderData != null && orderData.getOrderState() == OrderState.getOrderStateByCode(1).getOrderState()) {

			Order entityOrder = new Order();
			entityOrder.setOrderNumber(orderNumber);
			entityOrder.setOrderState(OrderState.getOrderStateByCode(2).getOrderState());
			// 更新订单状态
			orderServiceImpl.updateOrder(entityOrder);
			// 获取产品详情
			ProductLevel product = ProductLevel.getProductById(orderData.getProductId());
			// 更新用户级别
			User entityUser = new User();
			entityUser.setUserId(orderData.getUserId());
			entityUser.setUserLevel(ProductLevel.getProductById(orderData.getProductId()).getTypeId());
			userServiceImpl.updateUser(entityUser);
			// 增加用户充值记录
			UserRecharge rechargeData = userRechargeServiceImpl.selectRechargeByuserId(orderData.getUserId());
			UserRecharge entityRecharge = new UserRecharge();
			entityRecharge.setUserId(orderData.getUserId());
			entityRecharge.setIsValid(RechargeValid.getRechargeByCode(1).getValidCode());
			if (rechargeData != null && rechargeData.getRechargeId() > 0) {
				// 往上累加
				entityRecharge.setRechargeId(rechargeData.getRechargeId());
				entityRecharge.setTotalMoney(rechargeData.getTotalMoney() + product.getPrice());
				entityRecharge.setEndTime(DateUtil.addDay(rechargeData.getEndTime(), product.getDay()));
				entityRecharge.setValidDays(rechargeData.getValidDays() + product.getDay());
				userRechargeServiceImpl.updateRecharge(entityRecharge);

			} else {
				// 直接插入
				entityRecharge.setTotalMoney(product.getPrice());
				entityRecharge.setEndTime(DateUtil.addDay(new Date(), product.getDay()));
				entityRecharge.setValidDays(product.getDay());
				userRechargeServiceImpl.insertRecharge(entityRecharge);
			}
			// 更新用户账户给邀请人返还20%钱 用于提现
			double reward = product.getPrice() * SystemConfig.percentage;
			UserFinancial financial = userFinancialServiceImpl.selectFinancial(orderData.getUserId());
			UserFinancial userFinacial = new UserFinancial();
			userFinacial.setUserId(orderData.getUserId());
			if (financial != null && financial.getFinancialId() > 0) {
				// 更新
				userFinacial.setTotalMoney(financial.getTotalMoney() + reward);
				userFinacial.setTotalRevenue(financial.getTotalRevenue() + reward);
				userFinancialServiceImpl.updateFinancial(userFinacial);

			} else {
				// 插入一条
				userFinacial.setTotalMoney(reward);
				userFinacial.setTotalRevenue(reward);
				userFinacial.setPayAccount("");
				userFinacial.setPayRealName("");
				userFinacial.setPhone("");
				userFinancialServiceImpl.insertFinancial(userFinacial);
			}
			// 插入明细
			UserFinancialDetail detail = new UserFinancialDetail();
			detail.setUserId(orderData.getUserId());
			detail.setFinancialType(FinancialType.getFinancialTypeByCode(1).getTypeId());
			detail.setFinancialMoney(reward);
			detail.setOperateDate(new Date());
			detail.setOperateStatus(FinancialOperateStatus.getFinancialOperateStatusByCode(3).getStateCode());
			detail.setSourceNumber(orderNumber);
			userFinancialDetailServiceImpl.insertFinancialDetail(detail);
			// orderData.getProductId()
		}
	}
}
