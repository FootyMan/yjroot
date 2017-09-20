package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class WxResponseModel {

	@ApiModelProperty(value = "appid")
	private String appid;
	@ApiModelProperty(value = "商户Id")
	private String partnerid;
	@ApiModelProperty(value = "预支付ID")
	private String prepayid;
	@ApiModelProperty(value = "类型")
	private String trade_type;
	@ApiModelProperty(value = "签名")
	private String sign;
	@ApiModelProperty(value = "时间戳")
	private String timeStamp;
	@ApiModelProperty(value = "随机数")
	private String nonce_str;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}
	public String getPrepayid() {
		return prepayid;
	}
	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
}
