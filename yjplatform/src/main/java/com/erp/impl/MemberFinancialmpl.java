package com.erp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.bean.UserFinancial;
import com.erp.dao.MemberFinancialDao;

@Service
public class MemberFinancialmpl {

	@Autowired MemberFinancialDao  userFinancialMapperERP;
	
	/**
	 * 列表
	 * @return
	 */
	public List<UserFinancial> list(Map<String, Object> map)
	{
		return userFinancialMapperERP.list(map);
	}
	/**
	 * 列表
	 * @return
	 */
	public int count(Map<String, Object> map)
	{
		return userFinancialMapperERP.count(map);
	}
	/**
	 * 更新操作状态
	 * @param detail
	 * @return
	 */
	public int updateOperateStatus(Map<String, Object> map)
	{
		return userFinancialMapperERP.updateOperateStatus(map);
	}
}
