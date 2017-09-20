package com.myErp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.UserFinancialDetailMapper;
import com.myErp.manager.bean.UserFinancialDetail;
import com.myErp.utils.DBContextHolder;

@Service("userFinancialDetailService")
public class UserFinancialDetailServiceImpl {

	@Autowired
	private UserFinancialDetailMapper userFinancialDetailMapper;

	/**
	 * 添加
	 * 
	 * @param detail
	 * @return
	 */
	public int insertFinancialDetail(UserFinancialDetail detail) {
		DBContextHolder.setDBType("siteRead");
		return userFinancialDetailMapper.insertFinancialDetail(detail);
	}

	/**
	 * 更新
	 * 
	 * @param detail
	 * @return
	 */
	public int updateFinancialDetail(UserFinancialDetail detail) {
		DBContextHolder.setDBType("siteRead");
		return userFinancialDetailMapper.updateFinancialDetail(detail);
	}

	/**
	 * 查询
	 * 
	 * @param detail
	 * @return
	 */
	public List<UserFinancialDetail> selectFinancialDetail(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userFinancialDetailMapper.selectFinancialDetail(userId);
	}
}
