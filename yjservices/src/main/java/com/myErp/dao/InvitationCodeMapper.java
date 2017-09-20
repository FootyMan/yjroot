package com.myErp.dao;

import com.myErp.manager.bean.InvitationCode;

public abstract interface InvitationCodeMapper {

	public abstract int insertCode(InvitationCode code);

	public abstract InvitationCode selectCode(String code);

	/**
	 * 获取有效的邀请码
	 * @return
	 */
	public abstract InvitationCode selectCodeByvalid();

	public abstract int updateCode(String code);
}
