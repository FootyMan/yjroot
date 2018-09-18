package com.erp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.bean.HomeUser;
import com.erp.dao.HomeMemberDao;

@Service
public class HomeMemberImpl {
	@Autowired
	HomeMemberDao homeUserMapperERP;

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int insertHomeUser(HomeUser home) {
		return homeUserMapperERP.insertHomeUser(home);
	}

	/**
	 * 删除
	 * 
	 * @param user
	 * @return
	 */
	public int deleteHomeUser(int[] userIds) {
		return homeUserMapperERP.deleteHomeUser(userIds);
	}

	/**
	 * 查询
	 * 
	 * @param user
	 * @return
	 */
	public HomeUser selectHomeUserByUserId(int userId) {
		return homeUserMapperERP.selectHomeUserByUserId(userId);
	}

	/**
	 * 查询所有
	 * 
	 * @param user
	 * @return
	 */
	public List<HomeUser> selectHomeUserList() {
		return homeUserMapperERP.selectHomeUserList();
	}
}
