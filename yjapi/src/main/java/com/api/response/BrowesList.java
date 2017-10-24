package com.api.response;

import java.util.ArrayList;
import java.util.List;

public class BrowesList {
	public BrowesList() {
		list = new ArrayList<BrowesResponse>();
	}

	public List<BrowesResponse> getList() {
		return list;
	}

	public void setList(List<BrowesResponse> list) {
		this.list = list;
	}
	private List<BrowesResponse> list;

}
