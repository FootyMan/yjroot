package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class HomeResponseModel {

	@ApiModelProperty(value = "用户Id")
	private int userId;
	@ApiModelProperty(value = "用户头像")
	private String headImage;
	@ApiModelProperty(value = "用户级别1普通用户 2会员用户")
	private int level;
	@ApiModelProperty(value = "昵称")
	private String nickName;
	@ApiModelProperty(value = "年龄")
	private int age;
	@ApiModelProperty(value = "1男 2女")
	private int gender;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
}
