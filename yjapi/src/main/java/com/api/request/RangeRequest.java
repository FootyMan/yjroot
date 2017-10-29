package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class RangeRequest {

	@ApiModelProperty(value = "页码")
	private int pageIndex;
	@ApiModelProperty(value = "筛选条件 显示ID")
	private String showId;
	@ApiModelProperty(value = "筛选条件 性别")
	private int sex;
	@ApiModelProperty(value = "筛选条件 城市Id")
	private int cityId;
	@ApiModelProperty(value = "筛选条件 年龄区间 格式:15-20")
	private String ageSection;
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
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
}