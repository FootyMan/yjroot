package com.service.api.dao;

import java.util.List;

import com.service.bean.Product;

public abstract interface ProductMapper {

	/**
	 * 获取能购买的产品
	 * 
	 * @param order
	 * @return
	 */
	public abstract List<Product> selectProductlist();
	/**
	 * 根据ID获取产品
	 * 
	 * @param order
	 * @return
	 */
	public abstract Product selectProductById(int productId);
}
