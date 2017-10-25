package com.service.enums;

public enum PayType {

	wx(1, "微信"), ali(2, "支付宝");
	private int payId;
	private String payName;
	 
	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	private PayType(int payId, String payName) {
		this.payId = payId;
		this.payName = payName;
	}

	public static PayType GetPayType(int payId) {
		for (PayType state : values()) {
			if (state.getPayId() == payId) {
				return state;
			}
		}
		return null;
	}
}
