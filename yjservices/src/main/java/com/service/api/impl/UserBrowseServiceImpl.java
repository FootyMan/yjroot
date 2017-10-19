package com.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.UserBrowseMapper;
import com.service.bean.UserBrowse;
import com.service.utils.DBContextHolder;

@Service("userBrowseService")
public class UserBrowseServiceImpl {

	@Autowired
	private UserBrowseMapper userVisitorMapper;

	/**
	 * 添加最近访客
	 * 
	 * @param order
	 * @return
	 */
	public int insertBrowse(UserBrowse userBrowse) {
		DBContextHolder.setDBType("siteRead");
		return userVisitorMapper.insertBrowse(userBrowse);
	}

	/**
	 * 查询访客数量
	 * 
	 * @param order
	 * @return
	 */
	public int selectBrowseByNumber(int toUserId) {
		DBContextHolder.setDBType("siteRead");
		return userVisitorMapper.selectBrowseByNumber(toUserId);
	}

}
