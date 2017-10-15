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
		imgList=new ArrayList<String>();
	}

	@ApiModelProperty(value = "图片地址")
	private List<String>imgList;

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
}
