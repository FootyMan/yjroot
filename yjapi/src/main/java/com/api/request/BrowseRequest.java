package com.api.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * 最近访客请求
 * @author HCY
 *
 */
public class BrowseRequest {

	@ApiModelProperty(value = "1获取数量 2访问数据")
	private int type;
	@ApiModelProperty(value = "页码")
	private int pageIndex;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
}
