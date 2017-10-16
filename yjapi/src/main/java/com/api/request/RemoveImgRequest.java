package com.api.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * ( 删除图片请求
 * 
 * @author HCY
 *
 */
public class RemoveImgRequest {
	@ApiModelProperty(value = "要删除的图片ID")
	private int imgId;
	@ApiModelProperty(value = "要删除的图片路径")
	private String imgUrl;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

}
