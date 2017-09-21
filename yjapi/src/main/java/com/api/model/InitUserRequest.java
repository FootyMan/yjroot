package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class InitUserRequest {

	@ApiModelProperty(value = "经度")
	private double lon;
	@ApiModelProperty(value = "纬度")
	private double lat;
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
}
