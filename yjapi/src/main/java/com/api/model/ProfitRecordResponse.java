package com.api.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 收益记录返回
 * 
 * @author HCY
 *
 */
public class ProfitRecordResponse {

	@ApiModelProperty(value = "日期")
	private String date;
	@ApiModelProperty(value = "用户")
	private String user;
	@ApiModelProperty(value = "会员类型")
	private String type;
	@ApiModelProperty(value = "佣金")
	private String money;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
}
