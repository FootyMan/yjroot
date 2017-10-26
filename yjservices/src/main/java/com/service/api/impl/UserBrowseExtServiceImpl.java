package com.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.UserBrowseExtMapper;
import com.service.bean.UserBrowseExt;
import com.service.utils.DBContextHolder;

@Service("UserBrowseExtServiceImpl")
public class UserBrowseExtServiceImpl {

	@Autowired
	private UserBrowseExtMapper userBrowseExtMapper;

	/**
	 * 添加浏览
	 * 
	 * @param order
	 * @return
	 */
	public int insertBrowseExt(UserBrowseExt userBrowse) {
		DBContextHolder.setDBType("siteRead");
		return userBrowseExtMapper.insertBrowseExt(userBrowse);
	}

	/**
	 * 添加浏览
	 * 
	 * @param order
	 * @return
	 */
	public int updateBrowesExt(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userBrowseExtMapper.updateBrowesExt(userId);
	}

}
