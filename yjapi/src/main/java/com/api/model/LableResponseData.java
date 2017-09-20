package com.api.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class LableResponseData {
	public LableResponseData()
	{
		list=new ArrayList<LableResponseModel>();
	}

	@ApiModelProperty(value = "用户所选的标签数据")
	private List<LableResponseModel> list;

	public List<LableResponseModel> getList() {
		return list;
	}

	public void setList(List<LableResponseModel> list) {
		this.list = list;
	}

	 
}

