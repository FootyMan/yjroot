package com.service.utils;

/**
 * 结果对象
 * @author HCY
 *
 */
public class ResultModel {
	private boolean success=true;
	private String msg;
	 
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
