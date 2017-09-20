package com.myErp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.ProvinceMapper;
import com.myErp.manager.bean.Province;
import com.myErp.utils.DBContextHolder;

@Service("provinceService")
public class ProvinceServiceImpl {
	@Autowired
	private ProvinceMapper provinceMapper;

	public List<Province> selectProvinces() {
		DBContextHolder.setDBType("siteRead");
		return provinceMapper.selectProvinces();
	}
}
