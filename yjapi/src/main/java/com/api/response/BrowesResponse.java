package com.api.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 最近访问
 * 
 * @author HCY
 *
 */
public class BrowesResponse extends HomeResponse {
	
	@ApiModelProperty(value = "访客ID 用于删除")
	private int browseId;
	@ApiModelProperty(value = "访问时间 只有在最近访客里返回数据")
	private String browseData;

	public String getBrowseData() {
		return browseData;
	}

	public void setBrowseData(String browseData) {
		this.browseData = browseData;
	}

	public int getBrowseId() {
		return browseId;
	}

	public void setBrowseId(int browseId) {
		this.browseId = browseId;
	}
}
