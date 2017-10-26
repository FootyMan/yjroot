package com.service.api.dao;

import com.service.bean.UserBrowse;

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
	/**
	 * 喜欢ID
	 * @param likeId
	 * @return
	 */
	public abstract int deleteBrowsById(int Id);
	
	/**
	 * 是否存在浏览记录
	 * @param browse
	 * @return
	 */
	public abstract UserBrowse selectExistRecord(UserBrowse browse);
	
	/**
	 * 更新浏览次数
	 * @param browse
	 * @return
	 */
	public abstract int updateBrowesCount(UserBrowse browse);
	
	/**
	 * 更新访客已被查看
	 * @param toUserId
	 * @return
	 */
	public abstract int updateBrowesIsBrowse(int toUserId);
}
