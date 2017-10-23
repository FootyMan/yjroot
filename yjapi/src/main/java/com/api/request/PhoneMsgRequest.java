package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class PhoneMsgRequest {

	@ApiModelProperty(value = "手机号")
	private String phone;
	@ApiModelProperty(value = "1注册（要传手机号） 2绑定支付宝账号（传手机号） 3 提现（传手机号）4修改密码(传手机号） 5忘记密码(传手机号）")
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
