package com.service.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.service.utils.Pagination;

public class User implements Serializable{

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
	 *用户级别1普通用户 2包月会员 3半年会员 4年费会员
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
	 * 环信Id
	 */
	private String easemobId;
	/**
	 * 个推id
	 */
	private String getuiId;
	
	/**
	 * 最近访客
	 */
	private UserBrowse browse;
	/**
	 * 分页
	 */
	private Pagination page;
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
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
	public String getEasemobId() {
		return easemobId;
	}
	public void setEasemobId(String easemobId) {
		this.easemobId = easemobId;
	}
	public String getGetuiId() {
		return getuiId;
	}
	public void setGetuiId(String getuiId) {
		this.getuiId = getuiId;
	}
	public UserBrowse getBrowse() {
		return browse;
	}
	public void setBrowse(UserBrowse browse) {
		this.browse = browse;
	}

}
