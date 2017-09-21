package com.api.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class CityResponseParent {

	@ApiModelProperty(value = "城市ID")
	private int id;
	@ApiModelProperty(value = "城市名称")
	private String name;
	@ApiModelProperty(value = "子级")
	private List<CityResponseChild> cities;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CityResponseChild> getCities() {
		return cities;
	}

	public void setCities(List<CityResponseChild> cities) {
		this.cities = cities;
	}
}
