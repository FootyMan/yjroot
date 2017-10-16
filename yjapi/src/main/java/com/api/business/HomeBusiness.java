package com.api.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.request.HomeObject;
import com.api.request.HomeRequest;
import com.api.request.RangeRequest;
import com.api.request.baseRequest;
import com.api.response.HomeResponse;
import com.api.response.RangeDataResponse;
import com.api.response.RangeResponse;
import com.api.response.BaseResponse;
import com.api.utils.PageParameter;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.myErp.impl.UserServiceImpl;
import com.myErp.manager.bean.AppHomePagePaging;
import com.myErp.manager.bean.RangeParameter;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserDatum;
import com.myErp.utils.StringUtils;
import com.myErp.utils.SystemConfig;

@Service("HomeBusiness")
public class HomeBusiness {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private BusinessUtils businessUtils;

	/**
	 * 获取首页列表
	 * 
	 * @param request
	 * @return
	 */
	public BaseResponse<HomeObject> GetHomeList(baseRequest<HomeRequest> request) {
		BaseResponse<HomeObject> response = new BaseResponse<HomeObject>();
		// 首页对象
		HomeObject object = new HomeObject();
		// 传入body
		HomeRequest requestModel = request.getbody();
		// 如果是第一页查询首页推荐
		if (requestModel.getPageIndex() == 1) {
			List<HomeResponse> recommend = new ArrayList<HomeResponse>();
			List<User> recResult = userServiceImpl.HomeUserRecommend();
			for (User user : recResult) {
				recommend.add(businessUtils.EntityToModel(user));
			}
			object.setRecommend(recommend);
			// 查询推荐用户
		}
		//
		AppHomePagePaging pagePaging = PageParameter.GetHomePage(requestModel.getPageIndex(), requestModel.getSex());
		List<User> userData = userServiceImpl.HomeUserList(pagePaging);
		List<HomeResponse> list = new ArrayList<HomeResponse>();
		for (User user : userData) {
			list.add(businessUtils.EntityToModel(user));
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
	 * 获取附近的人列表
	 * @param request
	 * @return
	 */
	public BaseResponse<RangeResponse> GetRangeList(baseRequest<RangeRequest> request) {
		BaseResponse<RangeResponse> response = new BaseResponse<RangeResponse>();

		RangeResponse rangeResponse = new RangeResponse();
		RangeRequest requestModel = request.getbody();
		//
		RangeParameter rangeParameter = PageParameter.GetRangePage(requestModel.getPageIndex(), request.getUserId());
		// 如果经纬度小于等于0 证明当前位置不可访问 默认天安门
		if (request.getLat() <= 0 || request.getLon() <= 0) {
			request.setLat(ResultEnum.defaultLat);
			request.setLon(ResultEnum.defaultLon);
		}
		rangeParameter.setLat(request.getLat());
		rangeParameter.setLon(request.getLon());
		rangeParameter.setShowId(requestModel.getShowId());
		rangeParameter.setSex(requestModel.getSex());
		rangeParameter.setCityId(requestModel.getCityId());
		// 年龄区间截取
		if (!StringUtils.isEmpty(requestModel.getAgeSection())) {
			String ageSection = requestModel.getAgeSection().replaceAll(" ", "");
			String[] age = ageSection.split("-");
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
		model.setHeadImage(SystemConfig.ImgurlPrefix + user.getHeadImage());
		model.setSex(user.getDatum().getGender());
		model.setAge(user.getDatum().getAge());
		UserDatum datum = user.getDatum();
		model.setRange(ResponseUtils.GetRange(datum.getRangeM()));
		model.setSign(datum.getSign());
		model.setSexuat(datum.getSexuat());
		return model;
	}

}