package com.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

import io.swagger.annotations.ApiModelProperty;

/**
 * 初始化App数据
 * 
 * @author HCY
 *
 */
public class InitResponseAppData {
	public InitResponseAppData() {
		personality = new ArrayList<LableResponseModel>();
		sports = new ArrayList<LableResponseModel>();
		music = new ArrayList<LableResponseModel>();
		cate = new ArrayList<LableResponseModel>();
		tour = new ArrayList<LableResponseModel>();
		cityData = new ArrayList<CityResponseParent>();
	}

	@ApiModelProperty(value = "个性类")
	private List<LableResponseModel> personality;
	@ApiModelProperty(value = "运动类")
	private List<LableResponseModel> sports;
	@ApiModelProperty(value = "音乐类")
	private List<LableResponseModel> music;
	@ApiModelProperty(value = "美食类")
	private List<LableResponseModel> cate;
	@ApiModelProperty(value = "旅游类")
	private List<LableResponseModel> tour;
	@ApiModelProperty(value = "城市")
	private List<CityResponseParent> cityData;

	public List<CityResponseParent> getCityData() {
		return cityData;
	}

	public void setCityData(List<CityResponseParent> cityData) {
		this.cityData = cityData;
	}

	public List<LableResponseModel> getPersonality() {
		return personality;
	}

	public void setPersonality(List<LableResponseModel> personality) {
		this.personality = personality;
	}

	public List<LableResponseModel> getSports() {
		return sports;
	}

	public void setSports(List<LableResponseModel> sports) {
		this.sports = sports;
	}

	public List<LableResponseModel> getMusic() {
		return music;
	}

	public void setMusic(List<LableResponseModel> music) {
		this.music = music;
	}

	public List<LableResponseModel> getCate() {
		return cate;
	}

	public void setCate(List<LableResponseModel> cate) {
		this.cate = cate;
	}

	public List<LableResponseModel> getTour() {
		return tour;
	}

	public void setTour(List<LableResponseModel> tour) {
		this.tour = tour;
	}
}
