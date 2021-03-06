package com.service.api.dao;

import java.util.List;

import com.service.bean.UserImg;

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

	/**
	 * 删除图片
	 * 
	 * @param user
	 * @return
	 */
	public abstract int UpdateImageByImgId(int imgId);
}
