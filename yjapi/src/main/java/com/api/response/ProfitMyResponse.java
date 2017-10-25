package com.api.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 我的收益
 * @author HCY
 *
 */
public class ProfitMyResponse {

	@ApiModelProperty(value = "账号")
	private String account;
	@ApiModelProperty(value = "真实姓名")
	private String realName;
	@ApiModelProperty(value = "可提现金额")
	private double canMoney;
	@ApiModelProperty(value = "累计收入")
	private double totalMoney;
	@ApiModelProperty(value = "日累计提现上限")
	private double quota;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public double getQuota() {
		return quota;
	}
	public void setQuota(double quota) {
		this.quota = quota;
	}
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
}
