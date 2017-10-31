package com.erp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.service.utils.Pagination;

public class UserModel implements Serializable {

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
	private int deviceType;

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
	 * 注册邀请码
	 */
	private String registerCode;
	/**
	 * 年龄
	 */
	private int age;
	/**
	 * 1男 2女
	 */
	private int sex;
	private String sexName;
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
	/**
	 * 设备名称
	 */
	private String deviceTypeName;

	/**
	 * 个性签名
	 */
	private String sign;
	/**
	 * 图片库
	 */
	private List<UserImageModel> imgList;

	/**
	 * 首页是否存在
	 */
	private int isHomeUser;

	public List<UserImageModel> getImgList() {
		return imgList;
	}

	public void setImgList(List<UserImageModel> imgList) {
		this.imgList = imgList;
	}

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

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
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

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public String getDeviceTypeName() {
		return deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public int getIsHomeUser() {
		return isHomeUser;
	}

	public void setIsHomeUser(int isHomeUser) {
		this.isHomeUser = isHomeUser;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

}
