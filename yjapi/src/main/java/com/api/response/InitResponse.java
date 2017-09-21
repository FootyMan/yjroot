package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class InitResponse {
	@ApiModelProperty(value = "用户信息")
	private InitUserResponse user;
	public InitUserResponse getUser() {
		return user;
	}
	public void setUser(InitUserResponse user) {
		this.user = user;
	}
	
}
