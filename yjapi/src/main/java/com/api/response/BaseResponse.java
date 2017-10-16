package com.api.response;

import com.api.utils.ResultEnum;

import io.swagger.annotations.ApiModelProperty;

public class BaseResponse<T> {

	@ApiModelProperty(value = "返回码200正常  100验签错误 500服务器错误 250字段位空")
	private int code = ResultEnum.SuccessCode;
	@ApiModelProperty(value = "错误和提示消息")
	private String msg = "";
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
