package com.myErp.dao;

import java.util.List;

import com.myErp.manager.bean.Province;

public abstract interface ProvinceMapper {
	public abstract List<Province> selectProvinces();
}
