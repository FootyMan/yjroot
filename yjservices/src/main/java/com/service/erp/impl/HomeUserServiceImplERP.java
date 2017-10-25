package com.service.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.bean.HomeUser;
import com.service.erp.dao.HomeUserMapperERP;
import com.service.utils.DBContextHolder;

@Service("HomeUserServiceImplERP")
public class HomeUserServiceImplERP {
	@Autowired
	HomeUserMapperERP homeUserMapperERP;

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int insertHomeUser(HomeUser home) {
		DBContextHolder.setDBType("siteRead");
		return homeUserMapperERP.insertHomeUser(home);
	}

	/**
	 * 删除
	 * 
	 * @param user
	 * @return
	 */
	public int deleteHomeUser(int homeId) {
		DBContextHolder.setDBType("siteRead");
		return homeUserMapperERP.deleteHomeUser(homeId);
	}

	/**
	 * 查询
	 * 
	 * @param user
	 * @return
	 */
	public HomeUser selectHomeUserByUserId(int userId) {
		DBContextHolder.setDBType("siteRead");
		return homeUserMapperERP.selectHomeUserByUserId(userId);
	}

	/**
	 * 查询所有
	 * 
	 * @param user
	 * @return
	 */
	public List<HomeUser> selectHomeUserList() {
		DBContextHolder.setDBType("siteRead");
		return homeUserMapperERP.selectHomeUserList();
	}
}
