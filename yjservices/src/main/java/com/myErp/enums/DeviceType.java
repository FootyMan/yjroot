package com.myErp.enums;

public enum DeviceType {

	ios(1, "ios"), adndoid(2, "adnroid");
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
	
	private DeviceType(int StateCode, String desc) {
		this.desc = desc;
		this.StateCode = StateCode;
	}

	public static DeviceType getDeviceTypeByCode(int code) {
		for (DeviceType state : values()) {
			if (state.getStateCode() == code) {
				return state;
			}
		}
		return null;
	}
}
