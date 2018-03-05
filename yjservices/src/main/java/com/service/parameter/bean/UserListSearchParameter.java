package com.service.parameter.bean;

import com.service.utils.Pagination;

public class UserListSearchParameter {
	/**
	 * 分页
	 */
	private Pagination page;
	/**
	 * 是否是导入用户 0不是 1是
	 */
	private int isImport;
	/**
	 * 向外展示用户ID
	 */
	private String userNo;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 *用户级别1普通用户 2包月会员 3半年会员 4年费会员
	 */
	private int userLevel;
	/**
	 *用户昵称
	 */
	private String nickName;
	
	/**
	 * 1男2女
	 */
	private int gender;
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public int getIsImport() {
		return isImport;
	}
	public void setIsImport(int isImport) {
		this.isImport = isImport;
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
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	

}
