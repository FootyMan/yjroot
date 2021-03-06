package com.api.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 支付宝支付返回参数
 * @author HCY
 *
 */
public class AlipayResponse {

	public String getAlipayString() {
		return alipayString;
	}

	public void setAlipayString(String alipayString) {
		this.alipayString = alipayString;
	}

	@ApiModelProperty(value = "支付宝订单支付字符串")
	private String alipayString;
}
