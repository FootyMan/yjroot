package com.myErp.dao;

import com.myErp.manager.bean.Appversion;

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
