package com.service.bean;

import java.util.Date;

/**
 * 用户浏览表
 * @author HCY
 *
 */
public class UserBrowseExt {

	private int browseExt;
	private int userId;
	private Date browseTime;
	private int browseNumber;
	public int getBrowseExt() {
		return browseExt;
	}
	public void setBrowseExt(int browseExt) {
		this.browseExt = browseExt;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getBrowseTime() {
		return browseTime;
	}
	public void setBrowseTime(Date browseTime) {
		this.browseTime = browseTime;
	}
	public int getBrowseNumber() {
		return browseNumber;
	}
	public void setBrowseNumber(int browseNumber) {
		this.browseNumber = browseNumber;
	}
}
