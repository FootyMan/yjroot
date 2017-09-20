package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class RangeRequestModel {

	@ApiModelProperty(value = "页码")
	private int pageIndex;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	@ApiModelProperty(value = "经度")
	private double lon;
	@ApiModelProperty(value = "纬度")
	private double lat;
}
