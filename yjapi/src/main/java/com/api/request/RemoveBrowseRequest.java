package com.api.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * 删除访客
 * @author HCY
 *
 */
public class RemoveBrowseRequest {
	@ApiModelProperty(value = "访客Id 访客列表已返回")
	private int browseId;

	public int getBrowseId() {
		return browseId;
	}

	public void setBrowseId(int browseId) {
		this.browseId = browseId;
	}

}
