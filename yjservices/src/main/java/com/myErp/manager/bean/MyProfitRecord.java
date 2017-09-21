package com.myErp.manager.bean;

import java.sql.Date;

/**
 * 我的收益记录
 * @author HCY
 *
 */
public class MyProfitRecord {

	//操作日期
	private Date operateDate;
	//昵称
	private String nickName;
	//产品描述
	private String productDesc;
	//收益金额
	private double financialMoney;
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public double getFinancialMoney() {
		return financialMoney;
	}
	public void setFinancialMoney(double financialMoney) {
		this.financialMoney = financialMoney;
	}
}
