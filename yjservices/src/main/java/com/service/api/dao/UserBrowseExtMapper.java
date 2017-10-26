package com.service.api.dao;
import com.service.bean.UserBrowseExt;

public abstract interface UserBrowseExtMapper {

	/**
	 * 添加浏览
	 * 
	 * @param order
	 * @return
	 */
	public abstract int insertBrowseExt(UserBrowseExt userBrowse);
	/**
	 * 添加浏览
	 * 
	 * @param order
	 * @return
	 */
	public abstract int updateBrowesExt(int userId);
}
