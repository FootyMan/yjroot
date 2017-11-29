package com.api.request;


import io.swagger.annotations.ApiModelProperty;

public class IapRequest {

//	@ApiModelProperty(value = "对应的UserId")
	private int userId;
	@ApiModelProperty(value = "收据参数")
	private String receipt;
	//private String chooseEnv;
//	public String getIapId() {
//		return iapId;
//	}
//	public void setIapId(String iapId) {
//		this.iapId = iapId;
//	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
