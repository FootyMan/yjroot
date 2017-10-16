package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 我的图片
 * 
 * @author HCY
 *
 */
public class MyImageResponse {
	public List<UserImgResponse> getImgList() {
		return imgList;
	}

	public void setImgList(List<UserImgResponse> imgList) {
		this.imgList = imgList;
	}

	public MyImageResponse() {
		imgList = new ArrayList<UserImgResponse>();
	}

	@ApiModelProperty(value = "图片")
	private List<UserImgResponse> imgList;
}
