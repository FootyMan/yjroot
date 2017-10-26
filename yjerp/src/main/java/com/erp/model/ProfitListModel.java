package com.erp.model;

import com.service.utils.Pagination;

public class ProfitListModel {
	
	private Pagination page;
	private int userid;
	private String userNo;
	private String nickName;
	private double financialMoney;
	private String payAccount;
	private String payRealName;
	private double totalMoney;
	private String sourceNumber;
	private String operateDate;
	private int operateStatus;
	private String operateStatusName;
	
	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public double getFinancialMoney() {
		return financialMoney;
	}

	public void setFinancialMoney(double financialMoney) {
		this.financialMoney = financialMoney;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getPayRealName() {
		return payRealName;
	}

	public void setPayRealName(String payRealName) {
		this.payRealName = payRealName;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getSourceNumber() {
		return sourceNumber;
	}

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}

	public String getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public int getOperateStatus() {
		return operateStatus;
	}

	public void setOperateStatus(int operateStatus) {
		this.operateStatus = operateStatus;
	}

	public String getOperateStatusName() {
		return operateStatusName;
	}

	public void setOperateStatusName(String operateStatusName) {
		this.operateStatusName = operateStatusName;
	}
}
