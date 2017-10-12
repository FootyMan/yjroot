package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class HomeRequest {

	@ApiModelProperty(value = "页码")
	private int pageIndex;
	@ApiModelProperty(value = "0全部 1男 2女")
	private int sex;
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	 
}
