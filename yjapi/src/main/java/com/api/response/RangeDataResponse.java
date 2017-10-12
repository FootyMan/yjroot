package com.api.response;

import io.swagger.annotations.ApiModelProperty;

public class RangeDataResponse {
	@ApiModelProperty(value = "用户Id")
	private int userId;
	@ApiModelProperty(value = "用户头像")
	private String headImage;
	@ApiModelProperty(value = "用户级别 1普通用户 2包月会员 3半年会员 4年费会员")
	private int vip;
	@ApiModelProperty(value = "昵称")
	private String nickName;
	@ApiModelProperty(value = "年龄")
	private int age;
	@ApiModelProperty(value = "1男 2女")
	private int sex;
	@ApiModelProperty(value = "距离")
	private String range;
	@ApiModelProperty(value = "个性签名")
	private String sign;
	private String shape;
	@ApiModelProperty(value = "性取向")
	private String sexuat;
	public String getSexuat() {
		return sexuat;
	}

	public void setSexuat(String sexuat) {
		this.sexuat = sexuat;
	}

	public String getSign() {
		return sign;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

}
