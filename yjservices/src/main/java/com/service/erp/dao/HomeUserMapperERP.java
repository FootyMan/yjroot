package com.service.erp.dao;

import java.util.List;

import com.service.bean.HomeUser;

public abstract interface HomeUserMapperERP {

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public abstract int insertHomeUser(HomeUser home);

	/**
	 * 删除
	 * 
	 * @param user
	 * @return
	 */
	public abstract int deleteHomeUser(int homeId);
	/**
	 * 查询
	 * 
	 * @param user
	 * @return
	 */
	public abstract HomeUser selectHomeUserByUserId(int userId);
	/**
	 * 查询所有
	 * @return
	 */
	public abstract List<HomeUser> selectHomeUserList();
	
}
