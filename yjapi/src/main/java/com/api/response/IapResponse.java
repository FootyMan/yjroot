package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class IapResponse {

	@ApiModelProperty(value = "是否验证通过0没通过 1通过")
	private int isVerify;

	public int getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(int isVerify) {
		this.isVerify = isVerify;
	}
}
