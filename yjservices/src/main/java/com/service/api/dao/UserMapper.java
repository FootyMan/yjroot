package com.service.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.service.bean.*;
import com.service.utils.DBContextHolder;

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
	public abstract User selectUserByphone(String phone);//@Param("phone") 

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
	public abstract List<User> HomeUserList(AppHomePagePaging pagePaging);

	/**
	 * app用户首页数据Count
	 *
	 * @param user
	 * @return
	 */
	public abstract int HomeUserListCount(AppHomePagePaging pagePaging);

	/**
	 * app用户首页推荐
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> HomeUserRecommend();

	/**
	 * 附近的人
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> RangeUserList(RangeParameter rangeParameter);

	/**
	 * 附近的人数量
	 *
	 * @param user
	 * @return
	 */
	public abstract int RangelistCount(RangeParameter rangeParameter);

	/**
	 * 个人详情主页
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> getUserDetails(RangeParameter rangeParameter);

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
	public abstract List<User> userLikeList(AppHomePagePaging page);

	/**
	 * 用户访客浏览
	 *
	 * @param user
	 * @return
	 */
	public abstract List<User> userBrowseList(RangeParameter rangeParameter);

	/**
	 * 根据ID获取
	 * 
	 * @param user
	 * @return
	 */
	public abstract List<User> selectDatumByUserId(int userId);
	
	/**
	 * 更新导入用户资料
	 * @param user
	 * @return
	 */
	public abstract int updateImportUser(User user);
	/**
	 * 获取小秘书
	 * @return
	 */
	public abstract User GetSecretary();
}
