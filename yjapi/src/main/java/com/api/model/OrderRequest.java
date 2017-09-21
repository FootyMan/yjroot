package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class OrderRequest {

	@ApiModelProperty(value = "购买产品ID")
	private int productId;
	@ApiModelProperty(value = "支付类型 1微信 2支付宝 ")
	private int payType;

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}
