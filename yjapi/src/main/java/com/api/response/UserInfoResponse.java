package com.api.response;

import io.swagger.annotations.ApiModelProperty;

/***
 * 用户信息
 * 
 * @author HCY
 *
 */
public class UserInfoResponse {

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
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

	@ApiModelProperty(value = "用户ID用于请求接口")
	private int userId;
	@ApiModelProperty(value = "用户注册ID用于展示")
	private String showId;
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
	@ApiModelProperty(value = "城市Id")
	private int cityId;
	@ApiModelProperty(value = "城市名称")
	private String cityName;

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
	@ApiModelProperty(value = "资料是否完善")
	private boolean isFull;

	@ApiModelProperty(value = "环信Id")
	private String easeId;

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getEaseId() {
		return easeId;
	}

	public void setEaseId(String easeId) {
		this.easeId = easeId;
	}
}
