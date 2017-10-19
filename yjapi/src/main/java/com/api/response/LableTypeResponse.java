package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 标签
 * 
 * @author HCY
 *
 */
public class LableTypeResponse {
	public LableTypeResponse() {
		lableTypes = new ArrayList<LableResponse>();
	}

	@ApiModelProperty(value = "个性类")
	private List<LableResponse> lableTypes;

	@ApiModelProperty(value = "标签类型名")
	private String labelTypeName;

	public List<LableResponse> getLableTypes() {
		return lableTypes;
	}

	public void setLableTypes(List<LableResponse> lableTypes) {
		this.lableTypes = lableTypes;
	}

	public String getLabelTypeName() {
		return labelTypeName;
	}

	public void setLabelTypeName(String labelTypeName) {
		this.labelTypeName = labelTypeName;
	}

}
