package com.service.bean;

/**
 * 用户邀请记录
 * @author HCY
 *
 */
public class UserInvite {

	/**
	 * 主键
	 */
	private int inviteId;
	/**
	 * 邀请用户id
	 */
	private int inviteUserId;
	/**
	 * 邀请码
	 */
	private String inviteCode;
	public int getInviteId() {
		return inviteId;
	}
	public void setInviteId(int inviteId) {
		this.inviteId = inviteId;
	}
	public int getInviteUserId() {
		return inviteUserId;
	}
	public void setInviteUserId(int inviteUserId) {
		this.inviteUserId = inviteUserId;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	public int getRegisterUserId() {
		return registerUserId;
	}
	public void setRegisterUserId(int registerUserId) {
		this.registerUserId = registerUserId;
	}
	/**
	 *注册ID
	 */
	private int registerUserId;
}
