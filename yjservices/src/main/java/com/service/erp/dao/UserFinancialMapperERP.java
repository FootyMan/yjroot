package com.service.erp.dao;

import java.util.List;

import com.service.bean.UserFinancial;

public abstract interface UserFinancialMapperERP {
	
	/**
	 * 列表
	 * @return
	 */
	public abstract List<UserFinancial> selectWdListByPage();

}
