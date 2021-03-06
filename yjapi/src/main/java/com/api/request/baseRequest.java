package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class baseRequest<T> {

	@ApiModelProperty(value = "时间戳")
	private String timeStamp;

	@ApiModelProperty(value = "设备类型 1 ios 2 android")
	private int deviceType;

	@ApiModelProperty(value = "客户端版本")
	private String clientVersion;
	@ApiModelProperty(value = "版本号用于提示更新")
	private int versionCode;

	@ApiModelProperty(value = "key+时间戳=密文")
	private String sign;

	@ApiModelProperty(value = "用户ID")
	private int userId;

	@ApiModelProperty(value = "设备号")
	private String deviceToken;

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

	private T body;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public T getbody() {
		return body;
	}

	public void setbody(T body) {
		this.body = body;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
