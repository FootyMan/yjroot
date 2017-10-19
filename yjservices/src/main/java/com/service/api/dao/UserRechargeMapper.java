package com.service.api.dao;

import java.util.List;

import com.service.bean.UserRecharge;

public abstract interface UserRechargeMapper {

	/**
	 * 添加
	 * 
	 * @param recharge
	 * @return
	 */
	public abstract int insertRecharge(UserRecharge recharge);

	/**
	 * 更新
	 * 
	 * @param recharge
	 * @return
	 */
	public abstract int updateRecharge(UserRecharge recharge);

	/**
	 * 查询
	 * 
	 * @param recharge
	 * @return
	 */
	public abstract UserRecharge selectRechargeByuserId(int userId);

	/**
	 * 查询即将到期的会员
	 * 
	 * @param endTime
	 * @return
	 */

	public abstract List<UserRecharge> selectExpireUser(String endTime);

}
