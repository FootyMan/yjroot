package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class DetailsResponse {
	public DetailsResponse() {
		imgs = new ArrayList<DetailsImgResponse>();
		lables = new ArrayList<DetailsLableResponse>();
		user=new DetailsUserResponse();
	}
	
	public DetailsUserResponse getUser() {
		return user;
	}

	public void setUser(DetailsUserResponse user) {
		this.user = user;
	}

	public List<DetailsImgResponse> getImgs() {
		return imgs;
	}

	public void setImgs(List<DetailsImgResponse> imgs) {
		this.imgs = imgs;
	}

	public List<DetailsLableResponse> getLables() {
		return lables;
	}

	public void setLables(List<DetailsLableResponse> lables) {
		this.lables = lables;
	}
	@ApiModelProperty(value = "个人所有图片")
	private List<DetailsImgResponse> imgs;
	@ApiModelProperty(value = "个人所有标签")
	private List<DetailsLableResponse> lables;
	@ApiModelProperty(value = "用户基本信息")
	private DetailsUserResponse user;
}