package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class InitResponse {
	@ApiModelProperty(value = "用户信息")
	private UserInfoResponse user;
	public UserInfoResponse getUser() {
		return user;
	}
	public void setUser(UserInfoResponse user) {
		this.user = user;
	}
	
}
