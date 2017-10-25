package com.service.enums;

/**
 * 订单状态枚举
 * 
 * @author HCY
 *
 */
public enum OrderState {
	// 索引、描述、状态码
	Unpaid(0, "待付款"), Paid(10, "已付款");

	private int code;
	private String desc;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private OrderState(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static OrderState getOrderStateByCode(int code) {
		for (OrderState state : values()) {
			if (state.getCode() == code) {
				return state;
			}
		}
		return null;
	}
}
