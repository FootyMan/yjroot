package com.myErp.manager.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class User {

	/**
	 * 用户ID
	 */
	private int userId;
	/**
	 * 向外展示用户ID
	 */
	private String Id;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 设备类型1 ios 2andriond
	 */
	private int deviceType;
	/**
	 * 设备号
	 */
	private String deviceToken;
	/**
	 * 用户默认头像
	 */
	private String headImage;
	/**
	 *注册时间
	 */
	private Date registerTime;
	/**
	 *用户级别1普通用户 2vip会员 3黄金会员 3钻石会员 4最强荣耀 5最强王者
	 */
	private int userLevel;
	/**
	 *用户昵称
	 */
	private String nickName;
	/**
	 *非0为真 1正常 0禁用
	 */
	private int isDisable;
	/**
	 *是否注册环信 1注册 0没注册
	 */
	private int isEasemob;
	/**
	 *1ios 2andriod 3后台
	 */
	private int userSource;
	/**
	 * 邀请码
	 */
	private String inviteCode;
	private String passWord;
	/**
	 * 用户资料表
	 */
	private UserDatum datum;
	/**
	 * 浏览次数
	 */
	private int browseNumber;
	/**
	 * 浏览时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date browseDate;
	public Date getBrowseDate() {
		return browseDate;
	}
	public void setBrowseDate(Date browseDate) {
		this.browseDate = browseDate;
	}
	public int getBrowseNumber() {
		return browseNumber;
	}
	public void setBrowseNumber(int browseNumber) {
		this.browseNumber = browseNumber;
	}
	public UserDatum getDatum() {
		return datum;
	}
	public void setDatum(UserDatum datum) {
		this.datum = datum;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
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
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getIsDisable() {
		return isDisable;
	}
	public void setIsDisable(int isDisable) {
		this.isDisable = isDisable;
	}
	public int getIsEasemob() {
		return isEasemob;
	}
	public void setIsEasemob(int isEasemob) {
		this.isEasemob = isEasemob;
	}
	public int getUserSource() {
		return userSource;
	}
	public void setUserSource(int userSource) {
		this.userSource = userSource;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

}
