package com.erp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.impl.OrderImpl;
import com.erp.model.OrderModel;
import com.erp.utils.PageUtils;
import com.service.bean.Order;
import com.service.bean.Product;
import com.service.bean.User;
import com.service.enums.DeviceType;
import com.service.enums.OrderState;
import com.service.enums.PayType;
import com.service.utils.DateUtil;

@RequestMapping("/erp/order")
@Controller
public class OrderController extends BaseController {

	private String prefix = "erp/order";
	@Autowired
	OrderImpl orderImpl;
	
	@RequiresPermissions("erp:order:list")
	@GetMapping("")
	String order(Model model) {
		return prefix + "/list";
	}
	
	
	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		List<OrderModel> orderList = new ArrayList<OrderModel>();
		List<Order> orderData = orderImpl.list(params);
		for (Order o : orderData) {
			OrderModel m = new OrderModel();
			m.setOrderId(o.getOrderId());
			m.setOrderNumber(o.getOrderNumber());
			m.setUserId(o.getUserId());
			m.setOrderStateName(OrderState.getOrderStateByCode(o.getOrderState()).getDesc());
			m.setOrderSourceName(DeviceType.getDeviceTypeByCode(o.getOrderSource()).getDesc());
			m.setPayTypeName(PayType.GetPayType(o.getPayType()).getPayName());
			m.setOrderPrice(o.getOrderPrice());
			m.setOrderTime(DateUtil.getDateTime(o.getOrderTime()));
			User user = o.getUser();
			if (user != null) {
				m.setUserNo(user.getUserNo());
				m.setNickName(user.getNickName());
			}
			Product product = o.getProduct();
			if (product != null) {
				m.setProductDesc(product.getProductDesc());
				m.setTitle(product.getTitle());
			}
			orderList.add(m);
		}
		int total=orderImpl.count(params);
		PageUtils pageUtil = new PageUtils(orderList, total);
		return pageUtil;
		 
	}
}
