package com.api.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * 提现
 * @author HCY
 *
 */
public class ProfitWithDrawRequest {

	@ApiModelProperty(value = "提现金额")
	private double money;
	@ApiModelProperty(value = "手机验证码")
	private String verifyCode;
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
