package com.api.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户基本信息
 * 
 * @author HCYPOST /user/details
 *
 */
public class DetailsUserResponse extends UserInfoResponse {

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	@ApiModelProperty(value = "距离")
	private String range;

	@ApiModelProperty(value = "是否喜欢")
	private boolean isLike = false;
}
