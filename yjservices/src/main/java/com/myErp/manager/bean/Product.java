package com.myErp.manager.bean;

public class Product {

	//产品ID
	private int productId;
	//价格
	private double price;
	//天数
	private int day;
	//产品类型产品类型 1一个月 2半年 3一年
	private int productType;
	//产品描述
	private String productDesc;
	/**
	 * 会员标题
	 */
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
}
