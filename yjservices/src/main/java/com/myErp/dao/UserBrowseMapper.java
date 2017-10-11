package com.myErp.dao;

import com.myErp.manager.bean.UserBrowse;

public abstract interface  UserBrowseMapper {

	/**
	 * 添加最近访客
	 * 
	 * @param order
	 * @return
	 */
	public abstract int insertBrowse(UserBrowse userBrowse);
	/**
	 * 查询访客数量
	 * 
	 * @param order
	 * @return
	 */
	public abstract int selectBrowseByNumber(int toUserId);
}
