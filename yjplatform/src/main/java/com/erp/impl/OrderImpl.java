package com.erp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.bean.Order;
import com.erp.dao.OrderDao;

@Service
public class OrderImpl {

	@Autowired
	OrderDao OrderMapperERP;

	/**
	 * 后台订单列表
	 * 
	 * @return
	 */
	public List<Order> list(Map<String, Object> params) {
		return OrderMapperERP.list(params);
	}
	/**
	 * 后台订单列表
	 * 
	 * @return
	 */
	public int count(Map<String, Object> params) {
		return OrderMapperERP.count(params);
	}
}
