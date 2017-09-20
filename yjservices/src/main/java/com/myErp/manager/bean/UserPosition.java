package com.myErp.manager.bean;

/**
 * 用户地域位置表
 * @author HCY
 *
 */
public class UserPosition {

	/**
	 * 主键
	 */
	private int positionId;
	/**
	 * 是否在线
	 */
	private int isPosition;
	/**
	 * 用户ID
	 */
	private int userId;
	/***
	 * 经度
	 */
	private double longitude;
	/**
	 * 纬度
	 */
	private double latitude;
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public int getIsPosition() {
		return isPosition;
	}
	public void setIsPosition(int isPosition) {
		this.isPosition = isPosition;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 *  
	 * @return
	 */
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
