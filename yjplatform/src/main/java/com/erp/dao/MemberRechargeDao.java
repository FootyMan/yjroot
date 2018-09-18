package com.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.service.bean.UserRecharge;

@Mapper
public interface MemberRechargeDao {

	/**
	 * 查询
	 * 
	 * @param recharge
	 * @return
	 */
	List<UserRecharge> selectRechargeByuserId(Map<String, Object> map);

}
