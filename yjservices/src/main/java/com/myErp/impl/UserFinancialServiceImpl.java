package com.myErp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.UserFinancialMapper;
import com.myErp.manager.bean.UserFinancial;
import com.myErp.utils.DBContextHolder;

@Service("userFinancialService")
public class UserFinancialServiceImpl {

	@Autowired
	private UserFinancialMapper userFinancialMapper;

	/**
	 * 插入
	 * 
	 * @param userFinacial
	 * @return
	 */
	public int insertFinancial(UserFinancial userFinacial) {
		DBContextHolder.setDBType("siteRead");
		return userFinancialMapper.insertFinancial(userFinacial);

	}

	/**
	 * 更新
	 * 
	 * @param userFinacial
	 * @return
	 */
	public int updateFinancial(UserFinancial userFinacial) {
		DBContextHolder.setDBType("siteRead");
		return userFinancialMapper.updateFinancial(userFinacial);
	}

	/**
	 * 查询
	 * 
	 * @param userId
	 * @return
	 */
	public UserFinancial selectFinancial(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userFinancialMapper.selectFinancial(userId);
	}
}
