package com.myErp.dao;

import java.util.List;

import com.myErp.manager.bean.MyProfitRecord;
import com.myErp.manager.bean.UserFinancialDetail;

public abstract interface UserFinancialDetailMapper {

	/**
	 * 添加
	 * @param detail
	 * @return
	 */
	public abstract int insertFinancialDetail(UserFinancialDetail detail);
	/**
	 * 更新
	 * @param detail
	 * @return
	 */
	public abstract int updateFinancialDetail(UserFinancialDetail detail);
	/**
	 * 查询
	 * @param detail
	 * @return
	 */
	public abstract List<UserFinancialDetail> selectFinancialDetail(UserFinancialDetail detail);
	
	/**
	 * 我的收益记录
	 * @param detail
	 * @return
	 */
	public abstract List<MyProfitRecord> selectMyProfit(int userId);
}
