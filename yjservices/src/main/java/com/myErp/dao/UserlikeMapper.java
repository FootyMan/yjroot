package com.myErp.dao;

import com.myErp.manager.bean.Userlike;

public abstract interface UserlikeMapper {

	/**
	 * 添加喜欢的用户
	 * 
	 * @param order
	 * @return
	 */
	public abstract int insertUserlike(Userlike userlike);
	/**
	 * 删除喜欢的用户
	 * 
	 * @param order
	 * @return
	 */
	public abstract int deleteUserlike(Userlike userlike);
	
	/**
	 * 根据用户ID和喜欢ID获取
	 * @param userlike
	 * @return
	 */
	public abstract Userlike selectUserLikeById(Userlike userlike);
}
