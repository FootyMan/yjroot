package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ProductsResponse {
	public ProductsResponse()
	{
		products=new ArrayList<ProductDetailResponse>();
	}
	@ApiModelProperty(value = "购买产品")
	private List<ProductDetailResponse> products;
	@ApiModelProperty(value = "是否走内购 非0为真")
	private int isIap;
	
	public List<ProductDetailResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetailResponse> products) {
		this.products = products;
	}

	public int getIsIap() {
		return isIap;
	}

	public void setIsIap(int isIap) {
		this.isIap = isIap;
	}

}
