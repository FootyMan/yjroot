package com.api.request;

import io.swagger.annotations.ApiModelProperty;

public class UserDatumRequest {

	@ApiModelProperty(value = "年龄")
	private int age;
	@ApiModelProperty(value = "性别")
	private int gender;
	@ApiModelProperty(value = "城市")
	private String city;
	@ApiModelProperty(value = "体重")
	private String weight;
	@ApiModelProperty(value = "身高")
	private String height;
	@ApiModelProperty(value = "体型")
	private String shape;
	@ApiModelProperty(value = "性取向")
	private String sexuat;
	@ApiModelProperty(value = "个性签名")
	private String sign;
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
