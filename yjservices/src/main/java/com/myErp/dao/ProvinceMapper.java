package com.myErp.dao;

import java.util.List;

import com.myErp.manager.bean.Province;

public abstract interface ProvinceMapper {
	/**
	 * 获取所有城市
	 * 
	 * @return
	 */
	public abstract List<Province> selectProvinces();

	/**
	 * 根据ID获取城市
	 * 
	 * @param id
	 * @return
	 */
	public abstract Province SelectProvincesById(int id);
}
