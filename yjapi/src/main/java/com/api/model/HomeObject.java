package com.api.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class HomeObject {

	public HomeObject() {
		list = new ArrayList<HomeResponseModel>();
	}

	@ApiModelProperty(value = "推荐、只有PageIndex=1时此数组有值、其他无数据")
	private List<HomeResponseModel> recommend;

	@ApiModelProperty(value = "列表")
	private List<HomeResponseModel> list;

	public List<HomeResponseModel> getRecommend() {
		return recommend;
	}

	public void setRecommend(List<HomeResponseModel> recommend) {
		this.recommend = recommend;
	}

	public List<HomeResponseModel> getList() {
		return list;
	}

	public void setList(List<HomeResponseModel> list) {
		this.list = list;
	}

}
