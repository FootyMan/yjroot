package com.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.service.bean.User;
import com.service.parameter.bean.UserListSearchParameter;

@Mapper
public interface MemberDao {

	/**
	 * 后台用户列表
	 * 
	 * @param user
	 * @return
	 */
	List<User> UserList(Map<String, Object> map);

	/**
	 * 后台用户列表
	 * 
	 * @param user
	 * @return
	 */
	int UserListCount(Map<String, Object> map);

	/**
	 * 首页用户列表
	 * 
	 * @param user
	 * @return
	 */
	List<User> HomeUserList(Map<String, Object> map);

	/**
	 * 首页用户列表
	 * 
	 * @param user
	 * @return
	 */
	int HomeUserCount(Map<String, Object> map);

	/**
	 * 后台添加用户
	 * 
	 * @param user
	 * @return
	 */
	int InsertUserErp(User user);

	/**
	 * 详情
	 * 
	 * @param userId
	 * @return
	 */
	List<User> selectDetalsERP(int userId);

	/**
	 * 根据昵称是否存在
	 * 
	 * @param nickName
	 * @return
	 */

	int selectUserByNickName(String nickName);

	/**
	 * 查询所有环信id发送消息
	 * 
	 * @return
	 */
	List<String> selectEaseIdAll();

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
	public int setUserIsEnable(User user);
	/**
	 * 
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
}
