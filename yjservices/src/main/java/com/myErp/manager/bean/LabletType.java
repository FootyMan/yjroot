package com.myErp.manager.bean;

import java.security.KeyStore.PrivateKeyEntry;

public class LabletType {

	private int lableId;
	private String lableName;
	/**
	 * 1个性类 2运动类 3音乐类 4美食类 5旅游类
	 */
	private int lableType;
	public int getLableId() {
		return lableId;
	}
	public void setLableId(int lableId) {
		this.lableId = lableId;
	}
	public String getLableName() {
		return lableName;
	}
	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	public int getLableType() {
		return lableType;
	}
	public void setLableType(int lableType) {
		this.lableType = lableType;
	}

}
