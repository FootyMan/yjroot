package com.myErp.dao;

import com.myErp.manager.bean.UserInvite;

public abstract interface UserInviteMapper {

	/**
	 * 添加邀请记录
	 * @param userInvite
	 * @return
	 */
	public abstract int insertInvite(UserInvite userInvite);
}
