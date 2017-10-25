package com.service.bean;

import java.util.Date;

public class Appversion {

	/**
	 * 版本ID
	 */
	private int versionId;
	/**
	 * 设备类型 1ios 2android
	 */
	private int deviceType;
	/**
	 * 版本名称如1.0.0
	 */
	private String versionName;
	/**
	 * 版本号 从1递加
	 */
	private int versionCode;
	/**
	 * 版本日期
	 */
	private Date versionDate;
	/**
	 * 更新提示
	 */
	private String updateDescription;
	/**
	 * 下载地址
	 */
	private String downloadUrl;
	
	/**
	 * 是否强制更新 0可以关闭 1不可关闭
	 */
	private int isMust;
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public Date getVersionDate() {
		return versionDate;
	}
	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}
	public String getUpdateDescription() {
		return updateDescription;
	}
	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}
	public int getIsMust() {
		return isMust;
	}
	public void setIsMust(int isMust) {
		this.isMust = isMust;
	}

}
