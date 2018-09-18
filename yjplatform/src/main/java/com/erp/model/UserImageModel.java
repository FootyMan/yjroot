package com.erp.model;

public class UserImageModel {

	private int imageId;
	private int userId;
	private  String imgUrl;
	private int index;
	private String updateimgtag;
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getUpdateimgtag() {
		return updateimgtag;
	}
	public void setUpdateimgtag(String updateimgtag) {
		this.updateimgtag = updateimgtag;
	}
	
}
