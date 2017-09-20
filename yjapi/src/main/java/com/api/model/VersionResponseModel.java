package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class VersionResponseModel {

	 
	@ApiModelProperty(value = "是否需要更新 用于判断")
	private boolean existUp=false;
	public boolean isExistUp() {
		return existUp;
	}
	public void setExistUp(boolean existUp) {
		this.existUp = existUp;
	}
	@ApiModelProperty(value = "版本名称 1.0.0")
	private String versionName;
	@ApiModelProperty(value = "版本号 递增1")
	private int versionCode;
	@ApiModelProperty(value = "更新提示")
	private String updateDesc;
	@ApiModelProperty(value = "下载地址")
	private String downloadUrl;
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
	public String getUpdateDesc() {
		return updateDesc;
	}
	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
}
