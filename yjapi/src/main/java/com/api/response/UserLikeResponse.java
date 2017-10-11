package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class UserLikeResponse {

	@ApiModelProperty(value = "1喜欢 2不喜欢")
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
