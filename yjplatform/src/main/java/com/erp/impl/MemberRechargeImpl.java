package com.erp.impl;
import java.util.List;
import java.util.Map;

import com.erp.dao.MemberRechargeDao;
import com.service.bean.UserRecharge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRechargeImpl {

	@Autowired
	private MemberRechargeDao userRechargeMapper;

	 
	/**
	 * 查询
	 * 
	 * @param recharge
	 * @return
	 */
	public List<UserRecharge> selectRechargeByuserId(Map<String, Object> map) {
		return userRechargeMapper.selectRechargeByuserId(map);
	}
	
	 
}
