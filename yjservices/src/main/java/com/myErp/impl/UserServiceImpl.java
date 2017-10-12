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
	public List<User> HomeUserList(AppHomePagePaging pagePaging) {

		DBContextHolder.setDBType("siteRead");
		return userMappe.HomeUserList(pagePaging);
	}

	/**
	 * app用户首页数据Count
	 *
	 * @param user
	 * @return
	 */
	public int HomeUserListCount(AppHomePagePaging pagePaging) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.HomeUserListCount(pagePaging);
	}

	/**
	 * app用户首页推荐
	 *
	 * @param user
	 * @return
	 */
	public List<User> HomeUserRecommend() {
		DBContextHolder.setDBType("siteRead");
		return userMappe.HomeUserRecommend();
	}

	/**
	 * 附近的人
	 *
	 * @param user
	 * @return
	 */
	public List<User> RangeUserList(RangeParameter rangeParameter) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.RangeUserList(rangeParameter);
	}

	/**
	 * 附近的人分页数量
	 *
	 * @param user
	 * @return
	 */
	public int RangelistCount(RangeParameter rangeParameter) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.RangelistCount(rangeParameter);
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

	/**
	 * 用户喜欢列表
	 *
	 * @param user
	 * @return
	 */
	public List<User> userLikeList(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userMappe.userLikeList(userId);
	}

	/**
	 * 最近访客
	 *
	 * @param user
	 * @return
	 */
	public List<User> userBrowseList(RangeParameter rangeParameter) {
		DBContextHolder.setDBType("siteRead");
		List<User> list = userMappe.userBrowseList(rangeParameter);
		return list;
	}
}
