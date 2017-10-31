package com.erp.huashe;

public class HauSheList {
	private int code; 

	private String msg; 
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
	public ListInfo getInfo() {
		return info;
	}
	public void setInfo(ListInfo info) {
		this.info = info;
	}
	private ListInfo info;
}
