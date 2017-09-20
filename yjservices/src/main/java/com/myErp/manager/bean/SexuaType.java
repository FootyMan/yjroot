package com.myErp.manager.bean;

/**
 * 性取向
 * @author HCY
 *
 */
public class SexuaType {

	private int sexuaId;
	private String sexuaName;
	private int sort=0;
	public int getSexuaId() {
		return sexuaId;
	}
	public void setSexuaId(int sexuaId) {
		this.sexuaId = sexuaId;
	}
	public String getSexuaName() {
		return sexuaName;
	}
	public void setSexuaName(String sexuaName) {
		this.sexuaName = sexuaName;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
}
