package com.service.api.dao;

import com.service.bean.Appversion;

public abstract interface AppversionMapper {

	/**
	 * 添加
	 * @param version
	 * @return
	 */
	public abstract int insertAppversion(Appversion version);
	/**
	 * 根据设备查询
	 * @param version
	 * @return
	 */
	public abstract Appversion selectVersionByDevice(int deviceType);
}
