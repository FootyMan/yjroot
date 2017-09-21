package com.api.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 个人主页请求Model
 * @author HCY
 *
 */
public class DetailsRequest {

	@ApiModelProperty(value = "经度")
	private double lon;
	@ApiModelProperty(value = "纬度")
	private double lat;
	@ApiModelProperty(value = "要查看详情的用户ID")
	private int toUserId;
	public int getToUserId() {
		return toUserId;
	}
	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
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
}
