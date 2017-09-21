package com.api.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class LableRequestData {

	@ApiModelProperty(value = "标签数组")
	private List<LableRequest> list;

	public List<LableRequest> getList() {
		return list;
	}

	public void setList(List<LableRequest> list) {
		this.list = list;
	}

	 

}
