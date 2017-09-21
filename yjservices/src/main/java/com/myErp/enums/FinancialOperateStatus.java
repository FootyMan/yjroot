package com.myErp.enums;

public enum FinancialOperateStatus {

	Pending_settlement(1, "待结算"), Complete_settlement(2, "已结算"), Complete(10, "完成");
	private int StateCode;
	private String desc;
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
	private FinancialOperateStatus(int StateCode, String desc) {
		this.desc = desc;
		this.StateCode = StateCode;
	}

	public static FinancialOperateStatus getFinancialOperateStatusByCode(int code) {
		for (FinancialOperateStatus state : values()) {
			if (state.getStateCode() == code) {
				return state;
			}
		}
		return null;
	}
}
