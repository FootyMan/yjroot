package com.service.api.dao;

import com.service.bean.UserInvite;

public abstract interface UserInviteMapper {

	/**
	 * 添加邀请记录
	 * @param userInvite
	 * @return
	 */
	public abstract int insertInvite(UserInvite userInvite);
}
