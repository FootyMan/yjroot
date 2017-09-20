package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class InitResponseModel {

	@ApiModelProperty(value = "版本更新信息")
	private VersionResponseModel versionData;
	@ApiModelProperty(value = "二次启动页数据")
	private PageTwoResponseModel twoData;
	@ApiModelProperty(value = "用户信息")
	private InitUserResponseModel user;
	public InitUserResponseModel getUser() {
		return user;
	}
	public void setUser(InitUserResponseModel user) {
		this.user = user;
	}
	public VersionResponseModel getVersionData() {
		return versionData;
	}
	public void setVersionData(VersionResponseModel versionData) {
		this.versionData = versionData;
	}
	public PageTwoResponseModel getTwoData() {
		return twoData;
	}
	public void setTwoData(PageTwoResponseModel twoData) {
		this.twoData = twoData;
	}
	
}
