package com.service.bean;

import java.io.Serializable;

/**
 * 城市区域表
 * 
 * @author HCY
 *
 */
public class Province implements Serializable {

	 
	private static final long serialVersionUID = 7405049753749897267L;
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
