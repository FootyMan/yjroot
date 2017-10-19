package com.service.enums;

/**
 * 订单状态枚举
 * 
 * @author HCY
 *
 */
public enum OrderState {
	// 索引、描述、状态码
	Unpaid(1, "待付款", 1), Paid(2, "已付款", 10);

	private int code;
	private int orderState;
	private String desc;

	public int getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}

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

	private OrderState(int code, String desc, int orderState) {
		this.code = code;
		this.desc = desc;
		this.orderState = orderState;
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
