package com.service.bean;

public class UserImg {

	/**
	 * 图片ID
	 */
	private int imgId;
	/**
	 *  
	 */
	private int userId;
	/**
	 *  0都是缩略图
	 */
	private int imageType;
	/**
	 *  路径
	 */
	private String imagePath;
	/**
	 * 排序
	 */
	private int imageSort=0;
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getImageType() {
		return imageType;
	}
	public void setImageType(int imageType) {
		this.imageType = imageType;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getImageSort() {
		return imageSort;
	}
	public void setImageSort(int imageSort) {
		this.imageSort = imageSort;
	}
	
}

