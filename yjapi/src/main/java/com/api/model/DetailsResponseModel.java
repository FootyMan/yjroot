package com.api.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class DetailsResponseModel {
	public DetailsResponseModel() {
		imgs = new ArrayList<DetailsImgResponseModel>();
		lables = new ArrayList<DetailsLableResponseModel>();
	}

	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
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
	public List<DetailsImgResponseModel> getImgs() {
		return imgs;
	}
	public void setImgs(List<DetailsImgResponseModel> imgs) {
		this.imgs = imgs;
	}
	public List<DetailsLableResponseModel> getLables() {
		return lables;
	}
	public void setLables(List<DetailsLableResponseModel> lables) {
		this.lables = lables;
	}

	@ApiModelProperty(value = "用户头像")
	private String headImage;
	@ApiModelProperty(value = "用户显示ID")
	private String Id;
	@ApiModelProperty(value = "昵称")
	private String nickName;
	@ApiModelProperty(value = "1男 2女")
	private int gender;
	@ApiModelProperty(value = "年龄")
	private int age;
	@ApiModelProperty(value = "当前位置")
	private String city;
	@ApiModelProperty(value = "距离")
	private String range;
	@ApiModelProperty(value = "个性签名")
	private String sign;
	@ApiModelProperty(value = "体重")
	private String weight;
	@ApiModelProperty(value = "身高")
	private String height;
	@ApiModelProperty(value = "体型")
	private String shape;
	@ApiModelProperty(value = "性取向")
	private String sexuat;

	@ApiModelProperty(value = "个人所有图片")
	private List<DetailsImgResponseModel> imgs;
	@ApiModelProperty(value = "个人所有标签")
	private List<DetailsLableResponseModel> lables;
}