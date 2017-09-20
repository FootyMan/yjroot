package com.api.model;

import io.swagger.annotations.ApiModelProperty;

public class CityResponseChild {
	@ApiModelProperty(value = "城市ID")
	private int id;
	@ApiModelProperty(value = "城市名称")
	private String name;
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
}
