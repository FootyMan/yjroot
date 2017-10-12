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

import com.api.business.UserBusiness;
import com.api.request.*;
import com.api.response.HomeResponse;
import com.api.response.RangeDataResponse;
import com.api.response.RangeResponse;
import com.api.response.baseResponse;
import com.api.utils.PageParameter;
import com.api.utils.PageUtils;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.api.utils.decrypt.ResponseEncryptBody;
import com.myErp.impl.UserServiceImpl;
import com.myErp.manager.bean.AppHomePagePaging;
import com.myErp.manager.bean.LabletType;
import com.myErp.manager.bean.RangeParameter;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserDatum;
import com.myErp.utils.StringUtils;

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
	public baseResponse<HomeObject> HomeUser(@ApiParam(value = "输入") @RequestBody baseRequest<HomeRequest> request)
			throws Exception {
		baseResponse<HomeObject> response = new baseResponse<HomeObject>();
		// 首页对象
		HomeObject object = new HomeObject();
		// 传入body
		HomeRequest requestModel = request.getbody();
		// 如果是第一页查询首页推荐
		if (requestModel.getPageIndex() == 1) {
			List<HomeResponse> recommend = new ArrayList<HomeResponse>();
			List<User> recResult = userServiceImpl.HomeUserRecommend();
			for (User user : recResult) {
				recommend.add(UserBusiness.getInstance().EntityToModel(user));
			}
			object.setRecommend(recommend);
			// 查询推荐用户
		}
		//
		AppHomePagePaging pagePaging =  PageParameter.GetHomePage(requestModel.getPageIndex(), requestModel.getSex());
		List<User> userData = userServiceImpl.HomeUserList(pagePaging);
		List<HomeResponse> list = new ArrayList<HomeResponse>();
		for (User user : userData) {
			list.add(UserBusiness.getInstance().EntityToModel(user));
		}

		// 获取总页数
		if (list != null && list.size() > 0 && requestModel.getPageIndex() == 1) {
			int count = userServiceImpl.HomeUserListCount(pagePaging);
			object.setTotalPage(PageParameter.GetTotalPage(count, pagePaging.getPageSize()));
		}
		object.setList(list);
		response.setData(object);

		return response;
	}

	/**
	 * 附近的人
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/range", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-range", value = "附近的人 如果不让访问当前位置 经纬度不需要传、默认天安门", notes = "附近的人")
	public baseResponse<RangeResponse> RangeUser(@ApiParam(value = "输入") @RequestBody baseRequest<RangeRequest> request)
			throws Exception {
		baseResponse<RangeResponse> response = new baseResponse<RangeResponse>();

		RangeResponse rangeResponse = new RangeResponse();
		RangeRequest requestModel = request.getbody();
		//
		RangeParameter rangeParameter = PageParameter.GetRangePage(requestModel.getPageIndex(), request.getUserId());
		// 如果经纬度小于等于0 证明当前位置不可访问 默认天安门
		if (requestModel.getLat() <= 0 || requestModel.getLon() <= 0) {
			requestModel.setLat(ResultEnum.defaultLat);
			requestModel.setLon(ResultEnum.defaultLon);
		}
		rangeParameter.setLat(requestModel.getLat());
		rangeParameter.setLon(requestModel.getLon());
		rangeParameter.setShowId(requestModel.getShowId());
		rangeParameter.setSex(requestModel.getSex());
		rangeParameter.setCity(requestModel.getCity());
		// 年龄区间截取
		if (!StringUtils.isEmpty(requestModel.getAgeSection())) {
			String[] age = requestModel.getAgeSection().split("-");
			rangeParameter.setBeginAge(Integer.parseInt(age[0]));
			rangeParameter.setEndAge(Integer.parseInt(age[1]));
		}
		List<User> userData = userServiceImpl.RangeUserList(rangeParameter);
		List<RangeDataResponse> list = new ArrayList<RangeDataResponse>();
		for (User user : userData) {
			list.add(EntityToModelExt(user));
		}

		// 获取总页数
		if (list != null && list.size() > 0 && rangeParameter.getPageIndex() == 1) {
			int count = userServiceImpl.RangelistCount(rangeParameter);
			rangeResponse.setTotalPage(PageParameter.GetTotalPage(count, rangeParameter.getPageSize()));
		}
		rangeResponse.setList(list);
		response.setData(rangeResponse);
		return response;
	}

	/**
	 * 实体对象转换model
	 * 
	 * @param user
	 * @return
	 */
	public RangeDataResponse EntityToModelExt(User user) {
		RangeDataResponse model = new RangeDataResponse();
		model.setUserId(user.getUserId());
		model.setNickName(user.getNickName());
		model.setVip(user.getUserLevel());
		model.setHeadImage(user.getHeadImage());
		model.setSex(user.getDatum().getGender());
		model.setAge(user.getDatum().getAge());
		UserDatum datum = user.getDatum();
		model.setRange(ResponseUtils.GetRange(datum.getRangeM()));
		model.setSign(datum.getSign());
		model.setSexuat(datum.getSexuat());
		return model;
	}
}
