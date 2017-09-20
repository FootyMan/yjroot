package com.myErp.enums;

public enum FinancialType {

	Revenue(1, "收入", 1), Defray(1, "支出", 2);
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

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	private int typeId;

	private FinancialType(int code, String desc, int typeId) {
		this.code = code;
		this.desc = desc;
		this.typeId = typeId;
	}

	public static FinancialType getFinancialTypeByCode(int code) {
		for (FinancialType state : values()) {
			if (state.getCode() == code) {
				return state;
			}
		}
		return null;
	}
}
