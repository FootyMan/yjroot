package com.myErp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.ProductMapper;
import com.myErp.manager.bean.Product;
import com.myErp.utils.DBContextHolder;

@Service("productService")
public class ProductServiceImpl {

	@Autowired
	private ProductMapper productMapper;

	/**
	 * 获取能购买的产品
	 * 
	 * @param order
	 * @return
	 */
	public List<Product> selectProductlist() {
		DBContextHolder.setDBType("siteRead");
		return productMapper.selectProductlist();
	}

	/**
	 * 根据ID获取产品
	 * 
	 * @param order
	 * @return
	 */
	public Product selectProductById(int productId) {
		DBContextHolder.setDBType("siteRead");
		return productMapper.selectProductById(productId);
	}
}
