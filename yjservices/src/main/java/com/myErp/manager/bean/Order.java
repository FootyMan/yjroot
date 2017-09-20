package com.myErp.manager.bean;

import java.util.Date;

public class Order {

	//主键ID
	private int orderId;
	//订单号
	private String orderNumber;
	//用户id
	private int userId;
	//订单时间
	private Date orderTime;
	//购买产品Id
	private int productId;
	//订单状态1待付款  10已付款
	private int orderState;
	//订单价格
	private double orderPrice;
	//1IOS 2android
	private int orderSource;
	//支付类型1微信 2支付宝
	private int payType;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public int getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(int orderSource) {
		this.orderSource = orderSource;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
}
