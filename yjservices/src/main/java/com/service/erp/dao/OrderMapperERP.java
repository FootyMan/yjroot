package com.service.erp.dao;

import java.util.List;

import com.service.bean.Order;
import com.service.bean.OrderListParameter;

public abstract interface OrderMapperERP {

	/**
	 * 后台订单列表
	 * @return
	 */
	public abstract List<Order> selectListOrderByPage(OrderListParameter order);
}
