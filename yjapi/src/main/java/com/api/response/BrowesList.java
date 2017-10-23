package com.api.response;

import java.util.ArrayList;
import java.util.List;

public class BrowesList {
	public BrowesList() {
		list = new ArrayList<HomeResponse>();
	}

	public List<HomeResponse> getList() {
		return list;
	}

	public void setList(List<HomeResponse> list) {
		this.list = list;
	}
	private List<HomeResponse> list;

}
