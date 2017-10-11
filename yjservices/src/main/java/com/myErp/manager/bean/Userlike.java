package com.myErp.manager.bean;

import java.util.Date;

/**
 * 用户喜欢
 * @author HCY
 *
 */
public class Userlike {

	private int likeId;
	private int userId;
	private int likeUserId;
	private Date likeDate;
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLikeUserId() {
		return likeUserId;
	}
	public void setLikeUserId(int likeUserId) {
		this.likeUserId = likeUserId;
	}
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
}
