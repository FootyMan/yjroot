package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class HomeResponse {

	@ApiModelProperty(value = "用户Id")
	private int userId;
	@ApiModelProperty(value = "用户头像")
	private String headImage;
	@ApiModelProperty(value = "用户级别1普通用户 2会员用户")
	private int vip;
	@ApiModelProperty(value = "昵称")
	private String nickName;
	@ApiModelProperty(value = "年龄")
	private int age;
	@ApiModelProperty(value = "1男 2女")
	private int sex;
	@ApiModelProperty(value = "个性签名")
	private String sign;
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	 
}
