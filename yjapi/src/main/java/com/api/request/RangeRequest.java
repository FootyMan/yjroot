package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class RangeRequest {

	@ApiModelProperty(value = "页码")
	private int pageIndex;
	@ApiModelProperty(value = "筛选条件 显示ID")
	private int showId;
	@ApiModelProperty(value = "筛选条件 性别")
	private int sex;
	@ApiModelProperty(value = "筛选条件 城市")
	private String city;
	@ApiModelProperty(value = "筛选条件 年龄区间 格式:15-20")
	private String ageSection;
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAgeSection() {
		return ageSection;
	}
	public void setAgeSection(String ageSection) {
		this.ageSection = ageSection;
	}
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
