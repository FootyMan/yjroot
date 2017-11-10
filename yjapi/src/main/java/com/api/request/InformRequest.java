package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class InformRequest {
 
	@ApiModelProperty(value = "个人空间详情用户ID")
	private int detailId;
	@ApiModelProperty(value = "举报原因")
	private String reason;
 
	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
