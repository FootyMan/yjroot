package com.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.model.*;
import com.api.requestresponse.ResponseEncryptBody;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.myErp.impl.UserServiceImpl;
import com.myErp.manager.bean.AppHomePagePaging;
import com.myErp.manager.bean.LabletType;
import com.myErp.manager.bean.RangeParameter;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserDatum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/home", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "首页接口")
public class HomeController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	/**
	 * 首页用户
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-user", value = "首页用户", notes = "首页用户")
	public baseResponse<HomeObject> HomeUser(
			@ApiParam(value = "输入") @RequestBody baseRequest<HomeRequestModel> request) {
		baseResponse<HomeObject> response = new baseResponse<HomeObject>();
		try {
			// 首页对象
			HomeObject object = new HomeObject();
			// 传入body
			HomeRequestModel requestModel = request.getbody();
			// 如果是第一页查询首页推荐
			if (requestModel.getPageIndex() == 1) {
				List<HomeResponseModel> recommend = new ArrayList<HomeResponseModel>();
				List<User> recResult = userServiceImpl.appUserRecommend();
				for (User user : recResult) {
					recommend.add(EntityToModel(user));
				}
				object.setRecommend(recommend);
				// 查询推荐用户
			}
			//
			AppHomePagePaging pagePaging = new AppHomePagePaging();
			pagePaging.setPageIndex((requestModel.getPageIndex() - 1) * 10);
			pagePaging.setPageSize(10);
			pagePaging.setGender(requestModel.getGender());
			List<User> userData = userServiceImpl.appUserList(pagePaging);
			List<HomeResponseModel> list = new ArrayList<HomeResponseModel>();
			for (User user : userData) {
				list.add(EntityToModel(user));
			}
			object.setList(list);
			response.setData(object);
		} catch (Exception e) {
			response.setCode(500);
			response.setMsg("服务器错误!");
		}

		return response;
	}

	/**
	 * 首页用户
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/range", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-range", value = "附近的人 如果不让访问当前位置 经纬度不需要传、默认天安门", notes = "附近的人")
	public baseResponse<List<RangeResponseModel>> RangeUser(
			@ApiParam(value = "输入") @RequestBody baseRequest<RangeRequestModel> request) {
		baseResponse<List<RangeResponseModel>> response = new baseResponse<List<RangeResponseModel>>();

		RangeRequestModel requestModel = request.getbody();
		//
		RangeParameter rangeParameter = new RangeParameter();
		rangeParameter.setPageIndex((requestModel.getPageIndex() - 1) * 10);
		rangeParameter.setPageSize(10);
		rangeParameter.setUserId(request.getUserId());
		// 如果经纬度小于等于0 证明当前位置不可访问 默认天安门
		if (requestModel.getLat() <= 0 || requestModel.getLon() <= 0) {
			requestModel.setLat(ResultEnum.defaultLat);
			requestModel.setLon(ResultEnum.defaultLon);
		}
		rangeParameter.setLat(requestModel.getLat());
		rangeParameter.setLon(requestModel.getLon());
		List<User> userData = userServiceImpl.appRangeUser(rangeParameter);
		List<RangeResponseModel> list = new ArrayList<RangeResponseModel>();
		for (User user : userData) {
			list.add(EntityToModelExt(user));
		}
		response.setData(list);
		return response;
	}

	
	/**
	 * 实体对象转换model
	 * 
	 * @param user
	 * @return
	 */
	public RangeResponseModel EntityToModelExt(User user) {
		RangeResponseModel model = new RangeResponseModel();
		model.setUserId(user.getUserId());
		model.setNickName(user.getNickName());
		model.setLevel(user.getUserLevel());
		model.setHeadImage(user.getHeadImage());
		model.setGender(user.getDatum().getGender());
		model.setAge(user.getDatum().getAge());
		UserDatum datum = user.getDatum();
		model.setRange(ResponseUtils.GetRange(datum.getRangeM()));
		return model;
	}

	/**
	 * 实体对象转换model
	 * 
	 * @param user
	 * @return
	 */
	public HomeResponseModel EntityToModel(User user) {
		HomeResponseModel model = new HomeResponseModel();
		model.setUserId(user.getUserId());
		model.setNickName(user.getNickName());
		model.setLevel(user.getUserLevel());
		model.setHeadImage(user.getHeadImage());
		model.setGender(user.getDatum().getGender());
		model.setAge(user.getDatum().getAge());
		return model;
	}
}
