package com.myErp.enums;

public enum LableType {

	
	geXing(1, "个性"), PianHao(2, "偏好");
	private int StateCode;
	private String desc;
	public int getStateCode() {
		return StateCode;
	}
	public void setStateCode(int stateCode) {
		StateCode = stateCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	private LableType(int StateCode, String desc) {
		this.desc = desc;
		this.StateCode = StateCode;
	}

	public static LableType getLableTypeByCode(int code) {
		for (LableType state : values()) {
			if (state.getStateCode() == code) {
				return state;
			}
		}
		return null;
	}
}
