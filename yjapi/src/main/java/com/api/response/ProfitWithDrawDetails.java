package com.api.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 提现记录
 * @author HCY
 *
 */
public class ProfitWithDrawDetails {

	@ApiModelProperty(value = "日期")
	private String date;
	@ApiModelProperty(value = "提现金额")
	private String moeny;
	@ApiModelProperty(value = "结算状态")
	private String state;
	@ApiModelProperty(value = "结算状态Id 1待结算 2已结算")
	private int stateId;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

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
