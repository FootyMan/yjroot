package com.myErp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.UserImgMapper;
import com.myErp.manager.bean.UserImg;
import com.myErp.utils.DBContextHolder;

@Service("userImgService")
public class UserImgServiceImpl {

	@Autowired
	private UserImgMapper userImgMapper;

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int insertUserImg(List<UserImg> userImgs) {
		DBContextHolder.setDBType("siteRead");
		return userImgMapper.insertUserImg(userImgs);
	}

	/**
	 * 根据用户ID查询
	 * 
	 * @param user
	 * @return
	 */
	public List<UserImg> selectImgtByUserId(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userImgMapper.selectImgtByUserId(userId);
	}
}
