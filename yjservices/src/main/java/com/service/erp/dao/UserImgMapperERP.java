package com.service.erp.dao;

import java.util.List;

import com.service.bean.UserImg;

public abstract interface UserImgMapperERP {

	/**
	 * 批量更新
	 * 
	 * @param user
	 * @return
	 */
	public abstract int updateUserImgList(List<UserImg> userImgs);
	
	/**
	 * 更新图片无效
	 * @param userId
	 * @return
	 */
	public abstract int updateUserImgStatus(int userId);

 
}
