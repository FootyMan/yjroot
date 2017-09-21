package com.api.request;

import java.util.ArrayList;
import java.util.List;

import com.api.response.HomeResponse;

import io.swagger.annotations.ApiModelProperty;

public class HomeObject {

	public HomeObject() {
		list = new ArrayList<HomeResponse>();
	}

	@ApiModelProperty(value = "推荐、只有PageIndex=1时此数组有值、其他无数据")
	private List<HomeResponse> recommend;

	@ApiModelProperty(value = "列表")
	private List<HomeResponse> list;

	public List<HomeResponse> getRecommend() {
		return recommend;
	}

	public void setRecommend(List<HomeResponse> recommend) {
		this.recommend = recommend;
	}

	public List<HomeResponse> getList() {
		return list;
	}

	public void setList(List<HomeResponse> list) {
		this.list = list;
	}

}
