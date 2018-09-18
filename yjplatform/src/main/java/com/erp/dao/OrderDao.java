package com.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.service.bean.Order;

@Mapper
public interface OrderDao {

	/**
	 * 后台订单列表
	 * 
	 * @return
	 */
	public List<Order> list(Map<String, Object> params);
	/**
	 * 后台订单列表
	 * 
	 * @return
	 */
	public int count(Map<String, Object> params);
}
