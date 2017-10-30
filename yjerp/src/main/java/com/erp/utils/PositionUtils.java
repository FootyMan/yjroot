package com.erp.utils;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.erp.model.PositionModel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.service.utils.HttpClientUtil;

public class PositionUtils {

	/**
	 * 根据城市获取经纬度
	 * @param address
	 * @return
	 */
	public static PositionModel GetpositionByAddress(String address) {
		PositionModel position = new PositionModel();
		JsonParser parse = new JsonParser();
		try {
			String jsonStr = HttpClientUtil.get("http://api.map.baidu.com/geocoder?address=" + address
					+ "&output=json&key=Gd9p34Zgf7IDSCFgw6fnHFmpV4zZyIXl");
			JsonObject json = (JsonObject) parse.parse(jsonStr);
			JsonObject result = json.get("result").getAsJsonObject();
			JsonObject today = result.get("location").getAsJsonObject();
			position.setLon(Double.parseDouble(today.get("lng").getAsString()));
			position.setLat(Double.parseDouble(today.get("lat").getAsString()));
			return position;

		} catch (Exception e) {
			position.setLon(116.4039630);
			position.setLat(39.9151190);
		}
		return position;

	}
}
