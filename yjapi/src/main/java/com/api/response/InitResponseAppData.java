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
		//lables = new LablesResponse();
	}

//	public LablesResponse getLables() {
//		return lables;
//	}
//
//	public void setLables(LablesResponse lables) {
//		this.lables = lables;
//	}

//	@ApiModelProperty(value = "标签数组")
//	private LablesResponse lables;
	@ApiModelProperty(value = "版本更新信息")
	private VersionResponse versionData;
	@ApiModelProperty(value = "二次启动页数据")
	private PageTwoResponse twoData;
	// @ApiModelProperty(value = "城市")
	// private List<CityResponseParent> cityData;

	// public List<CityResponseParent> getCityData() {
	// return cityData;
	// }
	//
	// public void setCityData(List<CityResponseParent> cityData) {
	// this.cityData = cityData;
	// }

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
