package com.erp.dao;

import org.apache.ibatis.annotations.Mapper;

import com.service.bean.UserDatum;

@Mapper
public interface MemberDatumDao {

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int insertDatum(UserDatum datum);

	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 */
	public int updateDatum(UserDatum datum);

	/**
	 * 根据ID获取
	 * 
	 * @param user
	 * @return
	 */
	public UserDatum selectDatumByUserId(int userId);
}
