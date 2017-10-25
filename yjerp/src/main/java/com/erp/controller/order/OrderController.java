package com.erp.controller.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.erp.model.OrderModel;
import com.service.bean.Order;
import com.service.bean.Product;
import com.service.bean.User;
import com.service.enums.DeviceType;
import com.service.enums.OrderState;
import com.service.enums.PayType;
import com.service.erp.impl.OrderServiceImplERP;
import com.service.utils.DateUtil;
import com.service.utils.Pagination;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderServiceImplERP orderServiceImplERP;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(OrderModel orderModel, Model model) {// Employee

		Order orderParameter = new Order();
		Pagination pagination = orderModel.getPage();
		if (pagination == null) {
			pagination = new Pagination();
		}
		orderParameter.setPage(pagination);
		Pagination.threadLocal.set(pagination);

		List<OrderModel> orderList=new ArrayList<OrderModel>();
		List<Order> orderData=orderServiceImplERP.selectListOrderByPage();
		for (Order o : orderData) {
			OrderModel m=new OrderModel();
			m.setOrderId(o.getOrderId());
			m.setOrderNumber(o.getOrderNumber());
			m.setUserId(o.getUserId());
			m.setOrderStateName(OrderState.getOrderStateByCode(o.getOrderState()).getDesc());
			m.setOrderSourceName(DeviceType.getDeviceTypeByCode(o.getOrderSource()).getDesc());
			m.setPayTypeName(PayType.GetPayType(o.getPayType()).getPayName());
			m.setOrderPrice(o.getOrderPrice());
			m.setOrderTime(DateUtil.getDateTime(o.getOrderTime()));
			User user=o.getUser();
			if (user!=null) {
				m.setUserNo(user.getUserNo());
				m.setNickName(user.getNickName());
			}
			Product product=o.getProduct();
			if (product!=null) {
				m.setProductDesc(product.getProductDesc());
				m.setTitle(product.getTitle());
			}
			orderList.add(m);
		}
		model.addAttribute("orderList", orderList);
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/order/list");
	}
}
