package com.api.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 详情标签
 * @author HCY
 *
 */
public class DetailsLableResponse {
	@ApiModelProperty(value = "标签名称")
	private String lableName;

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
}
