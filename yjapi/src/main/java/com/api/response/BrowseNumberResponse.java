package com.api.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 访客数量
 * @author HCY
 *
 */
public class BrowseNumberResponse {

	public int getBrowseNumber() {
		return browseNumber;
	}

	public void setBrowseNumber(int browseNumber) {
		this.browseNumber = browseNumber;
	}

	@ApiModelProperty(value = "访客数量")
	private int browseNumber;
}
