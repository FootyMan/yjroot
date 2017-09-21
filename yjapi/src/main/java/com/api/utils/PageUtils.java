package com.api.utils;

/**
 * 分页枚举
 * @author HCY
 *
 */
public enum PageUtils {

	PageSize(10);

	private int value;

	private PageUtils(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
