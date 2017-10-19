package com.service.api.dao;

import com.service.bean.UserVerifyCode;
public abstract interface UserVerifyCodeMapper {

	/**
	 * 添加
	 * @param position
	 * @return
	 */
	public abstract int insertUserVerifyCode(UserVerifyCode code);
	/**
	 * 表中是否存储
	 * @param position
	 * @return
	 */
	public abstract UserVerifyCode selectCodeByphone(String phone); 
}
