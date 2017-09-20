package com.myErp.enums;

public enum FinancialOperateStatus {

	Pending_settlement(1, "待结算", 1), Complete_settlement(2, "已结算", 2), Complete(3, "完成", 10);
	private int code;
	private String desc;
	private int StateCode;
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
	public int getStateCode() {
		return StateCode;
	}
	public void setStateCode(int stateCode) {
		StateCode = stateCode;
	}
	private FinancialOperateStatus(int code, String desc, int StateCode) {
		this.code = code;
		this.desc = desc;
		this.StateCode = StateCode;
	}

	public static FinancialOperateStatus getFinancialOperateStatusByCode(int code) {
		for (FinancialOperateStatus state : values()) {
			if (state.getCode() == code) {
				return state;
			}
		}
		return null;
	}
}
