package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ProductsResponse {
	public ProductsResponse()
	{
		products=new ArrayList<ProductDetailResponse>();
	}
	
	public List<ProductDetailResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetailResponse> products) {
		this.products = products;
	}

	@ApiModelProperty(value = "购买产品")
	private List<ProductDetailResponse> products;

}
