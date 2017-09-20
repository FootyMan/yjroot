package com.api.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class LableRequestData {

	@ApiModelProperty(value = "标签数组")
	private List<LableRequestModel> list;

	public List<LableRequestModel> getList() {
		return list;
	}

	public void setList(List<LableRequestModel> list) {
		this.list = list;
	}

	 

}
