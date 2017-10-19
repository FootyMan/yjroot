package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class MyLableResponse {

	public MyLableResponse() {
		lables = new ArrayList<LableTypeResponse>();
	}

	public List<LableTypeResponse> getLables() {
		return lables;
	}

	public void setLables(List<LableTypeResponse> lables) {
		this.lables = lables;
	}

	@ApiModelProperty(value = "标签数组")
	private List<LableTypeResponse> lables;
}
