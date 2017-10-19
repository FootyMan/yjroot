package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class LableResponse {

	@ApiModelProperty(value = "标签ID唯一")
	private int lableId;
	@ApiModelProperty(value = "标签名称")
	private String lableName;
	@ApiModelProperty(value = "标签类型1个性类 2偏好设置")
	private int lableType;
	public boolean isMyLable() {
		return isMyLable;
	}
	public void setMyLable(boolean isMyLable) {
		this.isMyLable = isMyLable;
	}
	@ApiModelProperty(value = "是否是我的标签")
	private boolean isMyLable;
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
