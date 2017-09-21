package com.api.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 我的收益
 * @author HCY
 *
 */
public class ProfitMyResponse {

	@ApiModelProperty(value = "可提现金额")
	private double canMoney;
	@ApiModelProperty(value = "累计收入")
	private double totalMoney;
	public double getCanMoney() {
		return canMoney;
	}
	public void setCanMoney(double canMoney) {
		this.canMoney = canMoney;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
}
