package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class ProfitRecordRequest {

	@ApiModelProperty(value = "1收益记录 1提现明细")
	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
