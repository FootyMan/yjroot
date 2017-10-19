package com.service.erp.dao;

import java.util.List;

import com.service.bean.User;

public abstract interface UserMapperERP {

	/**
	 * 后台用户列表
	 * @param user
	 * @return
	 */
	public abstract List<User> ErpUserList(User user);
	
	/**
	 * 首页用户列表
	 * @param user
	 * @return
	 */
	public abstract List<User> HomeUserList(User user);
}
