package com.service.bean;

/**
 * 附近的人传入参数
 * @author HCY
 *
 */
public class RangeParameter {

	private int pageSize;
	/**
	 * 页码
	 */
	private int pageIndex;
	/**
	 * 纬度
	 */
	private double lat;
	/**
	 * 经度
	 */
	private double lon;
	
	private int userId;
	
	/**
	 * 显示ID
	 */
	private String showId;
	/**
	 * 性别
	 */
	private int sex;
	/**
	 * 地区Id
	 */
	private int cityId;
	/**
	 * 年龄范围
	 */
	private int beginAge;
	/**
	 * 年龄范围
	 */
	private int endAge;
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
	public int getBeginAge() {
		return beginAge;
	}
	public void setBeginAge(int beginAge) {
		this.beginAge = beginAge;
	}
	public int getEndAge() {
		return endAge;
	}
	public void setEndAge(int endAge) {
		this.endAge = endAge;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
}
