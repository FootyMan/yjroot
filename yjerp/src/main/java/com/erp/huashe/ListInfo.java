package com.erp.huashe;

import java.util.List;

public class ListInfo {

	public List<ListDiscovers> discovers;
	public List<ListDiscovers> recommends;

	private String page;
	private int page_count;
	private int page_size;
	private int total;
	public List<ListDiscovers> getDiscovers() {
		return discovers;
	}

	public void setDiscovers(List<ListDiscovers> discovers) {
		this.discovers = discovers;
	}

	public List<ListDiscovers> getRecommends() {
		return recommends;
	}

	public void setRecommends(List<ListDiscovers> recommends) {
		this.recommends = recommends;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getPage_count() {
		return page_count;
	}

	public void setPage_count(int page_count) {
		this.page_count = page_count;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
