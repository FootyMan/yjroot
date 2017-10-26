package com.service.erp.dao;

import java.util.List;

import com.service.bean.UserFinancial;
import com.service.bean.UserFinancialDetail;
import com.service.parameter.bean.FinancialParametERP;

public abstract interface UserFinancialMapperERP {
	
	/**
	 * 更新操作状态
	 * @param detail
	 * @return
	 */
	public abstract int updateOperateStatus(UserFinancialDetail detail);
	/**
	 * 列表
	 * @return
	 */
	public abstract List<UserFinancial> selectWdListByPage(FinancialParametERP paramet);

}
