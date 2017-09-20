package com.myErp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.UserMapper;
import com.myErp.manager.bean.AppHomePagePaging;
import com.myErp.manager.bean.RangeParameter;
import com.myErp.manager.bean.User;
import com.myErp.utils.DBContextHolder;

@Service("User")
public class UserServiceImpl {

	@Autowired
	private UserMapper userMappe;

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.insertUser(user);
	}

	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 */
	public int updateUser(User user) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.updateUser(user);
	}

	/**
	 * 根据用户id查询用户
	 * 
	 * @param user
	 * @return
	 */
	public User selectUserByUserId(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.selectUserByUserId(userId);
	}

	/**
	 * 根据邀请码是否存在
	 *
	 * @param user
	 * @return
	 */
	public User selectUserByInviteCode(String inviteCode) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.selectUserByInviteCode(inviteCode);
	}

	/**
	 * 根据手机号是否存在
	 *
	 * @param user
	 * @return
	 */
	public int selectUserByphone(String phone) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.selectUserByphone(phone);

	}

	/**
	 * 用户登录
	 *
	 * @param user
	 * @return
	 */
	public User userLogin(User user) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.userLogin(user);
	}

	/**
	 * app用户首页数据
	 *
	 * @param user
	 * @return
	 */
	public List<User> appUserList(AppHomePagePaging pagePaging) {

		DBContextHolder.setDBType("siteRead");
		return userMappe.appUserList(pagePaging);
	}

	/**
	 * app用户首页推荐
	 *
	 * @param user
	 * @return
	 */
	public List<User> appUserRecommend() {
		DBContextHolder.setDBType("siteRead");
		return userMappe.appUserRecommend();
	}

	/**
	 * 附近的人
	 *
	 * @param user
	 * @return
	 */
	public List<User> appRangeUser(RangeParameter rangeParameter) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.appRangeUser(rangeParameter);
	}

	/**
	 * 个人详情主页
	 *
	 * @param user
	 * @return
	 */
	public List<User> getUserDetails(RangeParameter rangeParameter) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.getUserDetails(rangeParameter);
	}

	/**
	 * 个人详情主页
	 *
	 * @param user
	 * @return
	 */
	public int updateBrowseNumber(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.updateBrowseNumber(userId);
	}

	/**
	 * 初始化用户
	 *
	 * @param user
	 * @return
	 */
	public List<User> initUser(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.initUser(userId);
	}
}
