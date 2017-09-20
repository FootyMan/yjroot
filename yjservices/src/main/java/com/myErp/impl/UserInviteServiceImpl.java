package com.myErp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.UserInviteMapper;
import com.myErp.manager.bean.UserInvite;
import com.myErp.utils.DBContextHolder;

@Service("userInviteService")
public class UserInviteServiceImpl {

	@Autowired
	private UserInviteMapper userInviteMapper;

	/**
	 * 添加邀请记录
	 * 
	 * @param userInvite
	 * @return
	 */
	public int insertInvite(UserInvite userInvite) {
		DBContextHolder.setDBType("siteRead");
		return userInviteMapper.insertInvite(userInvite);
	}

}
