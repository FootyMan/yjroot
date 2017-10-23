package com.service.bean;

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
	private String title;
	/**
	 *价格
	 */
	private int price; 
	//收益金额
	private double financialMoney;
	//"用户级别 1普通用户 2包月会员 3半年会员 4年费会员"
	private int userLevel;
	 
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
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
	 
	public double getFinancialMoney() {
		return financialMoney;
	}
	public void setFinancialMoney(double financialMoney) {
		this.financialMoney = financialMoney;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
