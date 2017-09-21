package com.api.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 提现记录
 * @author HCY
 *
 */
public class ProfitWithDrawResponse {

	@ApiModelProperty(value = "日期")
	private String date;
	@ApiModelProperty(value = "提现金额")
	private String moeny;
	@ApiModelProperty(value = "结算状态")
	private String state;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMoeny() {
		return moeny;
	}
	public void setMoeny(String moeny) {
		this.moeny = moeny;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
