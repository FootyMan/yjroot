package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class LableResponse {

	@ApiModelProperty(value = "标签ID唯一")
	private int lableId;
	@ApiModelProperty(value = "标签名称")
	private String lableName;
	@ApiModelProperty(value = "标签类型1个性类 2运动类 3音乐类 4美食类 5旅游类")
	private int lableType;
	public String getLableName() {
		return lableName;
	}
	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	public int getLableType() {
		return lableType;
	}
	public void setLableType(int lableType) {
		this.lableType = lableType;
	}
	public int getLableId() {
		return lableId;
	}
	public void setLableId(int lableId) {
		this.lableId = lableId;
	}
	
}
