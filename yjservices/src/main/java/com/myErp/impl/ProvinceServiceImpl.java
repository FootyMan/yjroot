package com.myErp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.ProvinceMapper;
import com.myErp.manager.bean.Province;
import com.myErp.redis.CityRedisManager;
import com.myErp.utils.DBContextHolder;

@Service("provinceService")
public class ProvinceServiceImpl {
	@Autowired
	private ProvinceMapper provinceMapper;

	public List<Province> selectProvinces() {
		DBContextHolder.setDBType("siteRead");
		return provinceMapper.selectProvinces();
	}

	/**
	 * 根据ID获取城市
	 * 
	 * @param id
	 * @return
	 */
	public Province SelectProvincesById(int id) {
		CityRedisManager manager=new CityRedisManager();
		//如果缓存为空查数据库
		Province obj=manager.GetCitySingle(id);
		if (obj==null) {
			DBContextHolder.setDBType("siteRead");
			return provinceMapper.SelectProvincesById(id);
		}
		return obj;
		
	}
}
