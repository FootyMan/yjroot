package com.myErp.manager.bean;

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
