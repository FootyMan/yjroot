package com.service.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.bean.User;
import com.service.erp.dao.UserMapperERP;
import com.service.utils.DBContextHolder;
@Service("UserServiceImplERP")
public class UserServiceImplERP {

	@Autowired
	private UserMapperERP userMapperERP;
	
	/**
	 * 后台用户列表
	 * @param user
	 * @return
	 */
	public List<User> GetUserList(User user)
	{
		DBContextHolder.setDBType("siteRead");
		return userMapperERP.ErpUserList(user);
	}
	/**
	 * 首页用户列表
	 * @param user
	 * @return
	 */
	public List<User> GetHomeUserList(User user)
	{
		DBContextHolder.setDBType("siteRead");
		return userMapperERP.HomeUserList(user);
	}
}
