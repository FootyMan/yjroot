package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class LableRequest {

	@ApiModelProperty(value = "标签id")
	private int lableId;
	@ApiModelProperty(value = "标签类型1个性类")
	private int lableType;
	@ApiModelProperty(value = "是否选择")
	private boolean isMyLable;

	public boolean isMyLable() {
		return isMyLable;
	}

	public void setMyLable(boolean isMyLable) {
		this.isMyLable = isMyLable;
	}

	public int getLableId() {
		return lableId;
	}

	public void setLableId(int lableId) {
		this.lableId = lableId;
	}

	public int getLableType() {
		return lableType;
	}

	public void setLableType(int lableType) {
		this.lableType = lableType;
	}
}
