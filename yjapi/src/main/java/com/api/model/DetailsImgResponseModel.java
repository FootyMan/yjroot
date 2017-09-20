package com.api.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户个人图片
 * @author HCY
 *
 */
public class DetailsImgResponseModel {

	@ApiModelProperty(value = "图片路径")
	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	} 
}
