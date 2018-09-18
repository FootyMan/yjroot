package com.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.ProvinceDao;
import com.service.bean.Province;
import com.service.redis.CityRedisManager;

@Service
public class ProvincImpl {
	@Autowired
	private ProvinceDao provinceMapper;

	public List<Province> selectProvinces() {
		return provinceMapper.selectProvinces();
	}

	/**
	 * 根据ID获取城市
	 * 
	 * @param id
	 * @return
	 */
	public Province SelectProvincesById(int id) {
		CityRedisManager manager = new CityRedisManager();
		// 如果缓存为空查数据库
		Province obj = manager.GetCitySingle(id);
		if (obj == null) {
			return provinceMapper.SelectProvincesById(id);
		}
		return obj;

	}

	/**
	 * 根据名称查询Id
	 * 
	 * @param name
	 * @return
	 */
	public Province SelectProvincesByName(String name) {
		return provinceMapper.SelectProvincesByName(name);
	}
}
