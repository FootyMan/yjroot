package com.api.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户个人图片
 * @author HCY
 *
 */
public class UserImgResponse {

	@ApiModelProperty(value = "图片路径")
	private String img;
	@ApiModelProperty(value = "图片Id")
	private int imgId;

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	} 
}
