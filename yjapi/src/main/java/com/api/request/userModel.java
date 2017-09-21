package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class userModel {

	@ApiModelProperty(value = "手机号")
	private String phone;
	
	@ApiModelProperty(value = "密码")
	private String passWord;
	
	@ApiModelProperty(value = "短信验证码")
	private String verifyCode;

	@ApiModelProperty(value = "邀请码")
	private String inviteCode;
	
	

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
