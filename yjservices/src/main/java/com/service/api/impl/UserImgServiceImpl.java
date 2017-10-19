package com.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.UserImgMapper;
import com.service.bean.UserImg;
import com.service.utils.DBContextHolder;

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

	/**
	 * 删除图片
	 * 
	 * @param user
	 * @return
	 */
	public int DeleteImageByImgId(int imgId) {
		DBContextHolder.setDBType("siteRead");
		return userImgMapper.deleteImageByImgId(imgId);
	}
}
