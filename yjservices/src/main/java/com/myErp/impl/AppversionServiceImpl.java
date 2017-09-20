package com.myErp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.AppversionMapper;
import com.myErp.manager.bean.Appversion;
import com.myErp.utils.DBContextHolder;

@Service("appversionService")
public class AppversionServiceImpl {
	@Autowired
	private AppversionMapper appversionMapper;

	/**
	 * 添加
	 * 
	 * @param version
	 * @return
	 */
	public int insertAppversion(Appversion version) {
		DBContextHolder.setDBType("siteRead");
		return appversionMapper.insertAppversion(version);
	}

	/**
	 * 根据设备查询
	 * 
	 * @param version
	 * @return
	 */
	public Appversion selectVersionByDevice(int deviceType) {
		DBContextHolder.setDBType("siteRead");
		return appversionMapper.selectVersionByDevice(deviceType);
	}
}
