package com.api.response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

import com.api.request.CityResponseParent;

import io.swagger.annotations.ApiModelProperty;

/**
 * 初始化App数据
 * 
 * @author HCY
 *
 */
public class InitResponseAppData {
	public InitResponseAppData() {
		personality = new ArrayList<LableResponse>();
		sports = new ArrayList<LableResponse>();
		music = new ArrayList<LableResponse>();
		cate = new ArrayList<LableResponse>();
		tour = new ArrayList<LableResponse>();
		cityData = new ArrayList<CityResponseParent>();
	}

	@ApiModelProperty(value = "版本更新信息")
	private VersionResponse versionData;
	@ApiModelProperty(value = "二次启动页数据")
	private PageTwoResponse twoData;

	@ApiModelProperty(value = "个性类")
	private List<LableResponse> personality;
	@ApiModelProperty(value = "运动类")
	private List<LableResponse> sports;
	@ApiModelProperty(value = "音乐类")
	private List<LableResponse> music;
	@ApiModelProperty(value = "美食类")
	private List<LableResponse> cate;
	@ApiModelProperty(value = "旅游类")
	private List<LableResponse> tour;
	@ApiModelProperty(value = "城市")
	private List<CityResponseParent> cityData;

	public List<CityResponseParent> getCityData() {
		return cityData;
	}

	public void setCityData(List<CityResponseParent> cityData) {
		this.cityData = cityData;
	}

	public List<LableResponse> getPersonality() {
		return personality;
	}

	public void setPersonality(List<LableResponse> personality) {
		this.personality = personality;
	}

	public List<LableResponse> getSports() {
		return sports;
	}

	public void setSports(List<LableResponse> sports) {
		this.sports = sports;
	}

	public List<LableResponse> getMusic() {
		return music;
	}

	public void setMusic(List<LableResponse> music) {
		this.music = music;
	}

	public List<LableResponse> getCate() {
		return cate;
	}

	public void setCate(List<LableResponse> cate) {
		this.cate = cate;
	}

	public List<LableResponse> getTour() {
		return tour;
	}

	public void setTour(List<LableResponse> tour) {
		this.tour = tour;
	}

	public VersionResponse getVersionData() {
		return versionData;
	}

	public void setVersionData(VersionResponse versionData) {
		this.versionData = versionData;
	}

	public PageTwoResponse getTwoData() {
		return twoData;
	}

	public void setTwoData(PageTwoResponse twoData) {
		this.twoData = twoData;
	}
}
