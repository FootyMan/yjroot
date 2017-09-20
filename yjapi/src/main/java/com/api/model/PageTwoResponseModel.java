package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class PageTwoResponseModel {

	@ApiModelProperty(value = "二次启动页图片路径 默认空 ")
	private String imgUrl="";

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
