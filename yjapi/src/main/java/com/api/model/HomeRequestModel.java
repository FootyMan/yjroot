package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class HomeRequestModel {

	@ApiModelProperty(value = "页码")
	private int pageIndex;
	@ApiModelProperty(value = "男女")
	private int gender;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
}
