package com.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.service.bean.UserFinancial;
 
@Mapper
public interface MemberFinancialDao {

	/**
	 * 更新操作状态
	 * 
	 * @param detail
	 * @return
	 */
	public int updateOperateStatus(Map<String, Object> map);

	/**
	 * 列表
	 * 
	 * @return
	 */
	public List<UserFinancial> list(Map<String, Object> map);
	/**
	 * 列表
	 * 
	 * @return
	 */
	public int count(Map<String, Object> map);


}
