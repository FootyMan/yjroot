package com.service.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.bean.UserFinancial;
import com.service.bean.UserFinancialDetail;
import com.service.erp.dao.UserFinancialMapperERP;
import com.service.parameter.bean.FinancialParametERP;
import com.service.utils.DBContextHolder;

@Service("UserFinancialServiceImplERP")
public class UserFinancialServiceImplERP {

	@Autowired UserFinancialMapperERP  userFinancialMapperERP;
	
	/**
	 * 列表
	 * @return
	 */
	public List<UserFinancial> selectWdListByPage(FinancialParametERP paramet)
	{
		DBContextHolder.setDBType("siteRead");
		return userFinancialMapperERP.selectWdListByPage(paramet);
	}
	/**
	 * 更新操作状态
	 * @param detail
	 * @return
	 */
	public int updateOperateStatus(UserFinancialDetail detail)
	{
		DBContextHolder.setDBType("siteRead");
		return userFinancialMapperERP.updateOperateStatus(detail);
	}
}
