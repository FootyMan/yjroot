package com.service.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.bean.User;
import com.service.erp.dao.UserMapperERP;
import com.service.parameter.bean.UserListSearchParameter;
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
	public List<User> ErpUserListByPage(UserListSearchParameter user) {
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
	/**
	 * 根据昵称是否存在
	 * @param nickName
	 * @return
	 */
	public int selectUserByNickName(String nickName)
	{
		DBContextHolder.setDBType("siteRead");
		return userMapperERP.selectUserByNickName(nickName);
	}
	/**
	 * 查询所有环信id发送消息
	 * @param nickName
	 * @return
	 */
	public List<String> selectEaseIdAll()
	{
		DBContextHolder.setDBType("siteRead");
		return userMapperERP.selectEaseIdAll();
	}
	
	/**
	 * 获取导入用户
	 * @return
	 */
	public List<User> ImportUser()
	{
		DBContextHolder.setDBType("siteRead");
		return userMapperERP.ImportUser();
	}
}
