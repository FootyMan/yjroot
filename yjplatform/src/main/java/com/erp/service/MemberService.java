package com.erp.service;

import java.util.List;
import java.util.Map;

import com.service.bean.User;

public interface MemberService {

	/**
	 * 后台用户列表
	 * 
	 * @param user
	 * @return
	 */
	List<User> GetUserList(Map<String, Object> map);

	/**
	 * 后台用户列表
	 * 
	 * @param user
	 * @return
	 */
	int GetUserListCount(Map<String, Object> map);

	/**
	 * 首页用户列表
	 * 
	 * @param user
	 * @return
	 */
	List<User> GetHomeUserList(Map<String, Object> map);

	/**
	 * 首页用户列表
	 * 
	 * @param user
	 * @return
	 */
	int GetHomeUserCount(Map<String, Object> map);

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
	 * @param user
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
	 * @param nickName
	 * @return
	 */
	List<String> selectEaseIdAll();

	/**
	 * 获取导入用户
	 * 
	 * @return
	 */
	List<User> ImportUser();

	/**
	 * 设置用户是否禁用 1启用 0禁用
	 * 
	 * @param user
	 * @return
	 */
	int setUserIsEnable(User user);

	/**
	 * 更新
	 * @param user
	 * @return
	 */
	int updateUser(User user);

}
