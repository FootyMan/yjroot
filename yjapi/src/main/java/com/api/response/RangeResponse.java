package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 附近的人
 * @author HCY
 *
 */
public class RangeResponse {
	public RangeResponse()
	{
		list=new ArrayList<RangeDataResponse>();
	}

	public List<RangeDataResponse> getList() {
		return list;
	}
	public void setList(List<RangeDataResponse> list) {
		this.list = list;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@ApiModelProperty(value = "分页数据")
	private List<RangeDataResponse> list;
	@ApiModelProperty(value = "总分页数 只有pageIndex=1时返回")
	private int totalPage;
}
