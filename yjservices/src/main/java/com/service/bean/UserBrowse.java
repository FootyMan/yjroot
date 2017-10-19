package com.service.bean;

import java.util.Date;

/**
 * 访客表
 * @author HCY
 *
 */
public class UserBrowse {

	private int Id;
	//访问者Id
	private int browseId;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getBrowseId() {
		return browseId;
	}
	public void setBrowseId(int browseId) {
		this.browseId = browseId;
	}
	public int getToUserId() {
		return toUserId;
	}
	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	public Date getBrowseDate() {
		return browseDate;
	}
	public void setBrowseDate(Date browseDate) {
		this.browseDate = browseDate;
	}
	public int getIsBrowse() {
		return isBrowse;
	}
	public void setIsBrowse(int isBrowse) {
		this.isBrowse = isBrowse;
	}
	//被访问者ID
	private int toUserId;
	//访问时间
	private Date browseDate;
	//是否浏览 0没浏览 1浏览
	private int isBrowse;
}
