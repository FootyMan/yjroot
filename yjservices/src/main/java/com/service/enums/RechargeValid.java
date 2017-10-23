package com.service.enums;

public enum RechargeValid {

	Effective(1,"有效",1),
	Invalid(2,"无效",2);
	private int code;
	private String desc;
	private int validCode;
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
	public int getValidCode() {
		return validCode;
	}
	public void setValidCode(int validCode) {
		this.validCode = validCode;
	}
	
	private RechargeValid(int code, String desc, int validCode) {
		this.code = code;
		this.desc = desc;
		this.validCode = validCode;
	}

	public static RechargeValid getRechargeByCode(int code) {
		for (RechargeValid level : values()) {
			if (level.getCode() == code) {
				return level;
			}
		}
		return null;
	}
}
