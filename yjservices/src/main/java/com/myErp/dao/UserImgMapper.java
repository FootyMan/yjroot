package com.myErp.dao;

import java.util.List;

import com.myErp.manager.bean.UserImg;

public abstract interface UserImgMapper {

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public abstract int insertUserImg(List<UserImg> userImgs);

	/**
	 * 根据用户ID查询
	 * 
	 * @param user
	 * @return
	 */
	public abstract List<UserImg> selectImgtByUserId(int userId);
}
