package com.service.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.UserFinancialDetailMapper;
import com.service.bean.MyProfitRecord;
import com.service.bean.UserFinancialDetail;
import com.service.utils.DBContextHolder;

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
	public List<UserFinancialDetail> selectFinancialDetail(UserFinancialDetail detail) {
		DBContextHolder.setDBType("siteRead");
		return userFinancialDetailMapper.selectFinancialDetail(detail);
	}

	/**
	 * 我的收益记录
	 * 
	 * @param detail
	 * @return
	 */
	public List<MyProfitRecord> selectMyProfit(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userFinancialDetailMapper.selectMyProfit(userId);
	}
}
