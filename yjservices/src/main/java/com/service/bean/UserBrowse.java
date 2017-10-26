package com.service.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 访客表
 * 
 * @author HCY
 *
 */
public class UserBrowse {

	private int keyId;
	// 访问者Id
	private int browseId;
	// 被访问者ID
	private int toUserId;
	// 访问时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date browseDate;
	// 是否浏览 0没浏览 1浏览
	private int isBrowse;
	/**
	 * browseId浏览toUserId次数
	 */
	private int browseNumber;

	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
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

	public int getBrowseNumber() {
		return browseNumber;
	}

	public void setBrowseNumber(int browseNumber) {
		this.browseNumber = browseNumber;
	}

}
