package com.api.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class DetailsResponse {
	public DetailsResponse() {
		imgs = new ArrayList<DetailsImgResponse>();
		lables = new ArrayList<DetailsLableResponse>();
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

	public List<DetailsImgResponse> getImgs() {
		return imgs;
	}

	public void setImgs(List<DetailsImgResponse> imgs) {
		this.imgs = imgs;
	}

	public List<DetailsLableResponse> getLables() {
		return lables;
	}

	public void setLables(List<DetailsLableResponse> lables) {
		this.lables = lables;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	@ApiModelProperty(value = "用户头像")
	private String headImage;
	@ApiModelProperty(value = "用户显示ID")
	private String Id;
	@ApiModelProperty(value = "昵称")
	private String nickName;
	@ApiModelProperty(value = "1男 2女")
	private int sex;

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
	@ApiModelProperty(value = "用户级别 1普通用户 2包月会员 3半年会员 4年费会员 ")
	private int vip;
	@ApiModelProperty(value = "是否喜欢")
	private boolean isLike=false;
	@ApiModelProperty(value = "个人所有图片")
	private List<DetailsImgResponse> imgs;
	@ApiModelProperty(value = "个人所有标签")
	private List<DetailsLableResponse> lables;
}