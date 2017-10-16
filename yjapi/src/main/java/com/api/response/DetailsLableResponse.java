package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class DetailsLableResponse {

	@ApiModelProperty(value = "标签名称")
	private String lableName;
	@ApiModelProperty(value = "标签Id")
	private int labelId;
	public String getLableName() {
		return lableName;
	}
	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	public int getLabelId() {
		return labelId;
	}
	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
}
