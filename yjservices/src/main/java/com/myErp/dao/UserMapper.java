package com.myErp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myErp.manager.bean.*;

public abstract interface UserMapper {

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public abstract int insertUser(User user);

	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 */
	public abstract int updateUser(User user);

	/**
	 * 根据用户id查询用户
	 *
	 * @param user
	 * @return
	 */
	public abstract User selectUserByUserId(int userId);

	/**
	 * 根据邀请码是否存在
	 *
	 * @param user
	 * @return
	 */
	public abstract User selectUserByInviteCode(String inviteCode);

	/**
	 * 根据手机号是否存在
	 *
	 * @param user
	 * @return
	 */
	public abstract int selectUserByphone(@Param("phone") String phone);

	/**
	 * 用户登录
	 *
	 * @param user
	 * @return
	 */
	public abstract User userLogin(User user);

	/**
	 * app用户首页数据
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> appUserList(AppHomePagePaging pagePaging);

	/**
	 * app用户首页推荐
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> appUserRecommend();

	/**
	 * 附近的人
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> appRangeUser(RangeParameter rangeParameter);

	/**
	 * 个人详情主页
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> getUserDetails(RangeParameter rangeParameter);

	/**
	 * 更新浏览次数
	 * 
	 * @param userId
	 * @return
	 */
	public abstract int updateBrowseNumber(int userId);

	/**
	 * 初始化用户
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> initUser(int userId);
	
	/**
	 * 用户喜欢列表
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> userLikeList(int userId);
	
	/**
	 * 用户访客浏览
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> userBrowseList(RangeParameter rangeParameter);
}
