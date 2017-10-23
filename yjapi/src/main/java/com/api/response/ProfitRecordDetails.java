package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class ProfitRecordDetails {

	@ApiModelProperty(value = "日期")
	private String date;
	@ApiModelProperty(value = "用户")
	private String user;
	@ApiModelProperty(value = "收入描述")
	private String desc;
//	@ApiModelProperty(value = "会员类型")
//	private String type;
	@ApiModelProperty(value = "佣金")
	private String money;
//	@ApiModelProperty(value = "用户级别 1普通用户 2包月会员 3半年会员 4年费会员")
//	private int vip;
//	public int getVip() {
//		return vip;
//	}
//	public void setVip(int vip) {
//		this.vip = vip;
//	}
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
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
