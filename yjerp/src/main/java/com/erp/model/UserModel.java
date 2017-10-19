package com.erp.model;

import java.io.Serializable;
import java.util.Date;

import com.service.utils.Pagination;

public class UserModel implements Serializable  { 

	private Pagination page;

	private int userId;
	private String userNo;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 设备类型1 ios 2andriond
	 */
	private String deviceType;

	/**
	 * 用户默认头像
	 */
	private String headImage;
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 用户级别1普通用户 2包月会员 3半年会员 4年费会员
	 */
	private String userLevel;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 非0为真 1正常 0禁用
	 */
	private String isDisable;

	/**
	 * 邀请码
	 */
	private String inviteCode;

	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 1男 2女
	 */
	private String sex;
	/**
	 * 城市
	 */
	private int cityId;
	private String cityName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 体重
	 */
	private String weight;
	/**
	 * 身高
	 */
	private String height;
	/**
	 * 体型
	 */
	private String shape;
	/**
	 * 行取向
	 */
	private String sexuat;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(String isDisable) {
		this.isDisable = isDisable;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
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

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

}
