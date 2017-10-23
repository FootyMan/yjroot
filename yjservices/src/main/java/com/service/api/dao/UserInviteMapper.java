package com.service.api.dao;

import com.service.bean.UserInvite;

public abstract interface UserInviteMapper {

	/**
	 * 添加邀请记录
	 * @param userInvite
	 * @return
	 */
	public abstract int insertInvite(UserInvite userInvite);
	
	/**
	 * 根据注册人 找到邀请人
	 * @param registerUserId
	 * @return
	 */
	public abstract UserInvite selectInviteByregisterId(int registerUserId);
}
