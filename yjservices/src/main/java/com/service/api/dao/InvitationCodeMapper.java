package com.service.api.dao;

import com.service.bean.InvitationCode;

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
