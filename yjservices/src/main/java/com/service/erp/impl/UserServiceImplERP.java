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
	 * 
	 * @param user
	 * @return
	 */
	public List<User> ErpUserListByPage(User user) {
		DBContextHolder.setDBType("siteRead");
		return userMapperERP.ErpUserListByPage(user);
	}

	/**
	 * 首页用户列表
	 * 
	 * @param user
	 * @return
	 */
	public List<User> GetHomeUserList(User user) {
		DBContextHolder.setDBType("siteRead");
		return userMapperERP.HomeUserList(user);
	}

	/**
	 * 后台添加用户
	 * 
	 * @param user
	 * @return
	 */
	public int InsertUserErp(User user) {
		DBContextHolder.setDBType("siteRead");
		return userMapperERP.InsertUserErp(user);
	}

	/**
	 * 详情
	 * 
	 * @param user
	 * @return
	 */
	public List<User> selectDetalsERP(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userMapperERP.selectDetalsERP(userId);
	}
}
