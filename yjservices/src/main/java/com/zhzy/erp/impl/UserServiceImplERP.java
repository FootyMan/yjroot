package com.zhzy.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.manager.bean.User;
import com.myErp.utils.DBContextHolder;
import com.zhzy.erp.dao.UserMapperERP;
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
