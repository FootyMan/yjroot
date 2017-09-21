package com.api.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户修改密码
 * @author HCY
 *
 */
public class UserPwdResponse {
	@ApiModelProperty(value = "手机号")
	private String phone;
	
	@ApiModelProperty(value = "密码")
	private String passWord;
	
	@ApiModelProperty(value = "短信验证码")
	private String verifyCode;

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
