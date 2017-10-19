package com.service.bean;

import java.util.Date;

public class UserFinancialDetail {

	// 详情ID
	private int recordID;
	// 用户ID
	private int userId;
	// 收支类型 1收入2提现
	private int financialType;
	// 收入和提现金额
	private double financialMoney;
	// 操作日期
	private Date operateDate;
	// 操作状态，1待结算2已结算 10完成 收入直接完成即可
	private int operateStatus;
	// 明细来源单号 例如 1 收入 为邀请人充值的订单号，2客户提现时则为提现单据号（按照订单号生成规则生成）
	private String sourceNumber;

	public int getRecordID() {
		return recordID;
	}

	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFinancialType() {
		return financialType;
	}

	public void setFinancialType(int financialType) {
		this.financialType = financialType;
	}

	public double getFinancialMoney() {
		return financialMoney;
	}

	public void setFinancialMoney(double financialMoney) {
		this.financialMoney = financialMoney;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public int getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(int operateStatus) {
		this.operateStatus = operateStatus;
	}

	public String getSourceNumber() {
		return sourceNumber;
	}

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

}
