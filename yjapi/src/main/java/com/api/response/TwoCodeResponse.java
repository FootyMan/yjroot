package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class TwoCodeResponse {

	@ApiModelProperty(value = "二维码地址")
	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
