package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class LableResponseData {
	public LableResponseData()
	{
		list=new ArrayList<LableResponse>();
	}

	@ApiModelProperty(value = "用户所选的标签数据")
	private List<LableResponse> list;

	public List<LableResponse> getList() {
		return list;
	}

	public void setList(List<LableResponse> list) {
		this.list = list;
	}

	 
}

