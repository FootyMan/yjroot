package com.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.UserVerifyCodeMapper;
import com.service.bean.UserVerifyCode;
import com.service.utils.DBContextHolder;

@Service("UserVerifyCode")
public class UserVerifyCodeServiceImpl {

	@Autowired
	private UserVerifyCodeMapper UserVerifyCode;

	/**
	 * 添加
	 * 
	 * @param position
	 * @return
	 */
	public int insertUserVerifyCode(UserVerifyCode code) {
		DBContextHolder.setDBType("siteRead");
		return UserVerifyCode.insertUserVerifyCode(code);
	}

	/**
	 * 表中是否存储
	 * 
	 * @param position
	 * @return
	 */
	public UserVerifyCode selectCodeByphone(String phone) {

		DBContextHolder.setDBType("siteRead");
		return UserVerifyCode.selectCodeByphone(phone);
	}

}
