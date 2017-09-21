package com.api.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 支付宝绑定账号
 * @author HCY
 *
 */
public class ProfitAccountRequest {

	@ApiModelProperty(value = "支付宝账号")
	private String account;
	@ApiModelProperty(value = "真是姓名")
	private String name;
	@ApiModelProperty(value = "验证码")
	private String verifyCode;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
