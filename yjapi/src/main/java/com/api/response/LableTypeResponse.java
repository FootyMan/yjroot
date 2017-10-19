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
	@ApiModelProperty(value = "显示的标签")
	private String displayLable;

	public String getDisplayLable() {
		return displayLable;
	}

	public void setDisplayLable(String displayLable) {
		this.displayLable = displayLable;
	}

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
