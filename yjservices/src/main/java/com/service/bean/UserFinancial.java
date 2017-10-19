package com.service.bean;

public class UserFinancial {

	//主键自增
	private int financialId;
	//用户ID
	private int userId;
	//用户真实姓名
	private String payRealName;
	//收款账号 支付宝账号
	private String payAccount;
	//手机号
	private String phone;
	//账户余额
	private double totalMoney;
	//账户累计总收入
	private double totalRevenue;
	//账户累计总支出
	private double totalWithDraw;
	public int getFinancialId() {
		return financialId;
	}
	public void setFinancialId(int financialId) {
		this.financialId = financialId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPayRealName() {
		return payRealName;
	}
	public void setPayRealName(String payRealName) {
		this.payRealName = payRealName;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public double getTotalWithDraw() {
		return totalWithDraw;
	}
	public void setTotalWithDraw(double totalWithDraw) {
		this.totalWithDraw = totalWithDraw;
	}
}
