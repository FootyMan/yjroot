package com.service.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.bean.Order;
import com.service.bean.OrderListParameter;
import com.service.erp.dao.OrderMapperERP;
import com.service.utils.DBContextHolder;

@Service("OrderServiceImplERP")
public class OrderServiceImplERP {

	@Autowired
	OrderMapperERP OrderMapperERP;

	/**
	 * 后台订单列表
	 * 
	 * @return
	 */
	public List<Order> selectListOrderByPage(OrderListParameter order) {
		DBContextHolder.setDBType("siteRead");
		return OrderMapperERP.selectListOrderByPage(order);
	}
}
