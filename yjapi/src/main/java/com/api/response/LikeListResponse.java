package com.api.response;

import java.util.ArrayList;
import java.util.List;

public class LikeListResponse {
	public LikeListResponse() {
		list = new ArrayList<HomeResponse>();
	}

	public List<HomeResponse> list;

	public List<HomeResponse> getList() {
		return list;
	}

	public void setList(List<HomeResponse> list) {
		this.list = list;
	}
}
