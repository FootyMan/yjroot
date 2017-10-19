package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class ProductDetailResponse {
	@ApiModelProperty(value = "产品ID")
	private int productId;
	@ApiModelProperty(value = "产品描述")
	private String desc;
	@ApiModelProperty(value = "价格")
	private double price;
	@ApiModelProperty(value = "会员标题")
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
