package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 提现记录
 * 
 * @author HCY
 *
 */
public class ProfitWithDrawList {
	public ProfitWithDrawList() {
		list = new ArrayList<ProfitWithDrawDetails>();
	}

	@ApiModelProperty(value = "累计提现")
	private double totalWithDraw;

	@ApiModelProperty(value = "提现记录")
	private List<ProfitWithDrawDetails> list;

	public double getTotalWithDraw() {
		return totalWithDraw;
	}

	public void setTotalWithDraw(double totalWithDraw) {
		this.totalWithDraw = totalWithDraw;
	}

	public List<ProfitWithDrawDetails> getList() {
		return list;
	}

	public void setList(List<ProfitWithDrawDetails> list) {
		this.list = list;
	}
	
}
