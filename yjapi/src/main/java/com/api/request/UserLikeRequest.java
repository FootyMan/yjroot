package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class UserLikeRequest {

	@ApiModelProperty(value = "喜欢用户ID")
	private int likeId;
	@ApiModelProperty(value = "1喜欢 2不喜欢")
	private int type;
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
