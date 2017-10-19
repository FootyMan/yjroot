package com.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.UserlikeMapper;
import com.service.bean.Userlike;
import com.service.utils.DBContextHolder;

@Service("UserlikeService")
public class UserlikeServiceImpl {

	@Autowired
	private UserlikeMapper userlikeMapper;

	/**
	 * 添加喜欢的用户
	 * 
	 * @param order
	 * @return
	 */
	public int insertUserlike(Userlike userlike) {
		DBContextHolder.setDBType("siteRead");
		return userlikeMapper.insertUserlike(userlike);
	}

	/**
	 * 删除喜欢的用户
	 * 
	 * @param order
	 * @return
	 */
	public int deleteUserlike(Userlike userlike) {
		DBContextHolder.setDBType("siteRead");
		return userlikeMapper.deleteUserlike(userlike);
	}

	/**
	 * 根据用户ID和喜欢ID获取
	 * 
	 * @param order
	 * @return
	 */
	public Userlike selectUserLikeById(Userlike userlike) {
		DBContextHolder.setDBType("siteRead");
		return userlikeMapper.selectUserLikeById(userlike);
	}
}
