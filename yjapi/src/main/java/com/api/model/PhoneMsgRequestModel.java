package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class PhoneMsgRequestModel {

	@ApiModelProperty(value = "手机号")
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
