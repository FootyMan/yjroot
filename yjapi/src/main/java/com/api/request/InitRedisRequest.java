package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class InitRedisRequest {

	@ApiModelProperty(value = "缓存类型 1城市 2 lable标签")
	private int catchType;

	/**
	 * 缓存类型 1城市 2 lable标签
	 * @return
	 */
	public int getCatchType() {
		return catchType;
	}

	public void setCatchType(int catchType) {
		this.catchType = catchType;
	}
}
