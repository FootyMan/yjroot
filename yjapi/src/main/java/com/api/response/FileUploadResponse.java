package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 文件上传
 * @author HCY
 *
 */
public class FileUploadResponse {
	public FileUploadResponse()
	{
		imgList=new ArrayList<UserImgResponse>();
	}

	@ApiModelProperty(value = "图片地址")
	private List<UserImgResponse>imgList;

	public List<UserImgResponse> getImgList() {
		return imgList;
	}

	public void setImgList(List<UserImgResponse> imgList) {
		this.imgList = imgList;
	}
}
