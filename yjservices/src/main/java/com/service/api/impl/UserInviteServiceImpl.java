package com.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.UserInviteMapper;
import com.service.bean.UserInvite;
import com.service.utils.DBContextHolder;

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
