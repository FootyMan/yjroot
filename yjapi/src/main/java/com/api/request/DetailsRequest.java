package com.api.request;

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
	private int  detailId;
	/**
	 * 要查看详情的用户ID
	 * @return
	 */
	public int getDetailId() {
		return detailId;
	}
	/**
	 * 要查看详情的用户ID
	 * @param detailId
	 */
	public void setDetailId(int detailId) {
		this.detailId = detailId;
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
