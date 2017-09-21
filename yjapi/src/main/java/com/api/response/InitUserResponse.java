package com.api.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 初始化用户信息
 * 
 * @author HCY
 *
 */
public class InitUserResponse {

	@ApiModelProperty(value = "用户ID用于请求接口")
	private int userId;
	@ApiModelProperty(value = "用户注册ID用于展示")
	private String id;
	@ApiModelProperty(value = "用户头像")
	private String headImage;
	@ApiModelProperty(value = "用户级别 1普通用户 2包月会员 3半年会员 4年费会员")
	private int vip;
	@ApiModelProperty(value = "昵称")
	private String nickName;
	@ApiModelProperty(value = "邀请码")
	private String inviteCode;
	@ApiModelProperty(value = "年龄")
	private int age;
	@ApiModelProperty(value = "1男 2女")
	private int sex;
	@ApiModelProperty(value = "城市")
	private String city;
	@ApiModelProperty(value = "体重")
	private String weight;

	@ApiModelProperty(value = "身高")
	private String height;
	@ApiModelProperty(value = "体型")
	private String shape;
	@ApiModelProperty(value = "性取向")
	private String sexuat;
	@ApiModelProperty(value = "个性签名")
	private String sign;

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getSexuat() {
		return sexuat;
	}

	public void setSexuat(String sexuat) {
		this.sexuat = sexuat;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

}
