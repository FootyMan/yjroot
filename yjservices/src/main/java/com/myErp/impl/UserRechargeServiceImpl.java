package com.myErp.impl;
import java.util.List;

import com.myErp.dao.UserRechargeMapper;
import com.myErp.manager.bean.UserRecharge;
import com.myErp.utils.DBContextHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRechargeService")
public class UserRechargeServiceImpl {

	@Autowired
	private UserRechargeMapper userRechargeMapper;

	/**
	 * 添加
	 * 
	 * @param recharge
	 * @return
	 */
	public int insertRecharge(UserRecharge recharge) {
		DBContextHolder.setDBType("siteRead");
		return userRechargeMapper.insertRecharge(recharge);
	}

	/**
	 * 更新
	 * 
	 * @param recharge
	 * @return
	 */
	public int updateRecharge(UserRecharge recharge) {
		DBContextHolder.setDBType("siteRead");
		return userRechargeMapper.updateRecharge(recharge);
	}

	/**
	 * 查询
	 * 
	 * @param recharge
	 * @return
	 */
	public UserRecharge selectRechargeByuserId(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userRechargeMapper.selectRechargeByuserId(userId);
	}
	
	/**
	 * 查询即将到期的会员
	 * 
	 * @param recharge
	 * @return
	 */
	public List<UserRecharge> GetExpireUser(String endTime) {
		DBContextHolder.setDBType("siteRead");
		return userRechargeMapper.selectExpireUser(endTime);
	}
}
