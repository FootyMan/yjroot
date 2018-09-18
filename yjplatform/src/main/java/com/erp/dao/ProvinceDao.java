package com.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.service.bean.Province;

@Mapper
public interface ProvinceDao {
	/**
	 * 获取所有城市
	 * 
	 * @return
	 */
	List<Province> selectProvinces();

	/**
	 * 根据ID获取城市
	 * 
	 * @param id
	 * @return
	 */
	Province SelectProvincesById(int id);

	/**
	 * 根据名称查询id
	 * 
	 * @param name
	 * @return
	 */
	Province SelectProvincesByName(String name);
}
