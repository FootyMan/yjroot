package com.service.erp.dao;

import java.util.List;

import com.service.bean.User;
import com.service.parameter.bean.UserListSearchParameter;

public abstract interface UserMapperERP {

	/**
	 * 后台用户列表
	 * 
	 * @param user
	 * @return
	 */
	public abstract List<User> ErpUserListByPage(UserListSearchParameter user);

	/**
	 * 首页用户列表
	 * 
	 * @param user
	 * @return
	 */
	public abstract List<User> HomeUserList(User user);

	/**
	 * 后台添加用户
	 * 
	 * @param user
	 * @return
	 */
	public abstract int InsertUserErp(User user);

	/**
	 * 详情
	 * 
	 * @param userId
	 * @return
	 */
	public abstract List<User> selectDetalsERP(int userId);

	/**
	 * 根据昵称是否存在
	 * 
	 * @param nickName
	 * @return
	 */

	public abstract int selectUserByNickName(String nickName);

	/**
	 * 查询所有环信id发送消息
	 * 
	 * @return
	 */
	public abstract List<String> selectEaseIdAll();

	/**
	 * 获取导入用户
	 * 
	 * @return
	 */
	public List<User> ImportUser();

	/**
	 * 设置用户是否禁用 1启用 0禁用
	 * 
	 * @param user
	 * @return
	 */
	public abstract int setUserIsEnable(User user);
}
