package com.service.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.bean.UserFinancial;
import com.service.erp.dao.UserFinancialMapperERP;
import com.service.utils.DBContextHolder;

@Service("UserFinancialServiceImplERP")
public class UserFinancialServiceImplERP {

	@Autowired UserFinancialMapperERP  userFinancialMapperERP;
	
	/**
	 * 列表
	 * @return
	 */
	public List<UserFinancial> selectWdListByPage()
	{
		DBContextHolder.setDBType("siteRead");
		return userFinancialMapperERP.selectWdListByPage();
	}
}
