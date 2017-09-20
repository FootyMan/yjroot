package com.myErp.manager.bean;

/**
 * 用户资料
 * @author HCY
 *
 */
public class UserDatum {
	/**
	 * 主键
	 */
	private int datumId;
	/**
	 * 用户ID
	 */
	private int userId;
	/**
	 *年龄
	 */
	private int age;
	/**
	 *1男 2女
	 */
	private int gender;
	/**
	 * 城市
	 */
	private String city;
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
	 * 距离
	 */
	private double rangeM;
	public double getRangeM() {
		return rangeM;
	}
	public void setRangeM(double rangeM) {
		this.rangeM = rangeM;
	}
	/**
	 * 个性签名
	 */
	private String sign;
	public int getDatumId() {
		return datumId;
	}
	public void setDatumId(int datumId) {
		this.datumId = datumId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	

}
