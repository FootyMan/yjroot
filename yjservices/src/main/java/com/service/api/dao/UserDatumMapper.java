package com.service.api.dao;

import com.service.bean.UserDatum;

public abstract interface UserDatumMapper {

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public abstract int insertDatum(UserDatum datum);
	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 */
	public abstract int updateDatum(UserDatum datum);
	/**
	 * 根据ID获取
	 * 
	 * @param user
	 * @return
	 */
	public abstract UserDatum selectDatumByUserId(int userId);
}
