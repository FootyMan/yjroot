package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 收益记录返回
 * 
 * @author HCY
 *
 */
public class ProfitRecordList {
	public ProfitRecordList() {
		list = new ArrayList<ProfitRecordDetails>();
	}

	@ApiModelProperty(value = "累计收益")
	private double totalRevenue;
	@ApiModelProperty(value = "收益记录")
	private List<ProfitRecordDetails> list;
	 
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public List<ProfitRecordDetails> getList() {
		return list;
	}
	public void setList(List<ProfitRecordDetails> list) {
		this.list = list;
	}
}
