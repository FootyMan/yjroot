package com.api.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * 个人主页请求Model
 * @author HCY
 *
 */
public class DetailsRequest {

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
}
