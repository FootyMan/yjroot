package com.service.bean;

/**
 * 邀请码
 * @author HCY
 *
 */
public class InvitationCode {

	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	//是否有效 0无效 
	private int isValid;
}
