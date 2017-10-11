package com.myErp.manager.bean;

import java.util.Date;

/**
 * 不喜欢
 * @author HCY
 *
 */
public class UserDislike {

	private int dislikeId;
	private int userId;
	private int dislikeUserId;
	private Date dislikeDate;
	public int getDislikeId() {
		return dislikeId;
	}
	public void setDislikeId(int dislikeId) {
		this.dislikeId = dislikeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getDislikeUserId() {
		return dislikeUserId;
	}
	public void setDislikeUserId(int dislikeUserId) {
		this.dislikeUserId = dislikeUserId;
	}
	public Date getDislikeDate() {
		return dislikeDate;
	}
	public void setDislikeDate(Date dislikeDate) {
		this.dislikeDate = dislikeDate;
	}
}
