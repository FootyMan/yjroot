package com.service.bean;

import java.util.Date;

public class HomeUser {

	private int homeId;
	private int userId;
	//首页类型1 推荐、2首页下拉
	private int homeType;
	private Date createTime;
	private int sortColumn;
	public int getHomeId() {
		return homeId;
	}
	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getHomeType() {
		return homeType;
	}
	public void setHomeType(int homeType) {
		this.homeType = homeType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(int sortColumn) {
		this.sortColumn = sortColumn;
	}
	
	
}
