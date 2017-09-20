package com.myErp.manager.bean;

/**
 * 城市区域表
 * @author HCY
 *
 */
public class Province {

	private int provinceId;
	private int parentId;
	private String name;
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
