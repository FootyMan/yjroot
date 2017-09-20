package com.myErp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.OrderMapper;
import com.myErp.manager.bean.Order;
import com.myErp.utils.DBContextHolder;

@Service("orderService")
public class OrderServiceImpl {

	@Autowired
	private OrderMapper orderMapper;

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @return
	 */
	public int insertOrder(Order order) {
		DBContextHolder.setDBType("siteRead");
		return orderMapper.insertOrder(order);
	}

	/**
	 * 根据订单号查询订单
	 * 
	 * @param order
	 * @return
	 */
	public Order selectOrderByNumber(String orderNumber) {
		DBContextHolder.setDBType("siteRead");
		return orderMapper.selectOrderByNumber(orderNumber);
	}

	/**
	 * 更新订单
	 * 
	 * @param order
	 * @return
	 */
	public int updateOrder(Order order) {
		DBContextHolder.setDBType("siteRead");
		return orderMapper.updateOrder(order);
	}

}
