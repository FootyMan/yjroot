package com.myErp.dao;

import com.myErp.manager.bean.Order;

public abstract interface OrderMapper {

	/**
	 * 添加订单
	 * 
	 * @param order
	 * @return
	 */
	public abstract int insertOrder(Order order);

	/**
	 * 根据订单号查询订单
	 * 
	 * @param order
	 * @return
	 */
	public abstract Order selectOrderByNumber(String orderNumber);
	/**
	 * 更新订单
	 * 
	 * @param order
	 * @return
	 */
	public abstract int updateOrder(Order order);
}
