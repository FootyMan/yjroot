package com.myErp.dao;

import com.myErp.manager.bean.UserRecharge;

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
}
