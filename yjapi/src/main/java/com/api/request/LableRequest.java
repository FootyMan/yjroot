package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class LableRequest {

	@ApiModelProperty(value = "标签id")
	private int lableId;
	@ApiModelProperty(value = "标签类型1个性类 2运动类 3音乐类 4美食类 5旅游类")
	private int lableType;
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
