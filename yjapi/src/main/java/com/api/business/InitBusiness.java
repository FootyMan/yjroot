package com.api.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.request.InitRedisRequest;
import com.api.request.baseRequest;
import com.api.response.InitResponse;
import com.api.response.InitResponseAppData;
import com.api.response.LableTypeResponse;
import com.api.response.PageTwoResponse;
import com.api.response.TwoCodeResponse;
import com.api.response.UserInfoResponse;
import com.api.response.VersionResponse;
import com.api.response.BaseResponse;
import com.api.utils.ResultEnum;
import com.service.api.impl.AppversionServiceImpl;
import com.service.api.impl.LabletTypeServiceImpl;
import com.service.api.impl.ProvinceServiceImpl;
import com.service.api.impl.UserPositionServiceImpl;
import com.service.api.impl.UserServiceImpl;
import com.service.bean.Appversion;
import com.service.bean.LabletType;
import com.service.bean.Province;
import com.service.bean.User;
import com.service.bean.UserDatum;
import com.service.bean.UserPosition;
import com.service.redis.CityRedisManager;
import com.service.utils.ResultModel;
import com.service.utils.SystemConfig;

@Service("InitBusiness")
public class InitBusiness {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserPositionServiceImpl userPositionServiceImpl;
	@Autowired
	private LabletTypeServiceImpl labletTypeServiceImpl;
	@Autowired
	private AppversionServiceImpl appversionServiceImpl;
	@Autowired
	private BusinessUtils businessUtils;
	@Autowired
	private ProvinceServiceImpl provinceServiceImpl;

	/**
	 * 初始化用户
	 * 
	 * @param user
	 * @return
	 */
	public BaseResponse<InitResponse> initUser(baseRequest<?> user) {

		BaseResponse<InitResponse> response = new BaseResponse<InitResponse>();
		UserPosition position = new UserPosition();
		position.setIsPosition(1);
		if (user.getLon() <= 0) {
			user.setLon(ResultEnum.defaultLon);
		}
		if (user.getLat() <= 0) {
			user.setLat(ResultEnum.defaultLat);
			position.setIsPosition(0);
		}

		position.setUserId(user.getUserId());
		position.setLongitude(user.getLon());
		position.setLatitude(user.getLat());
		int positionId = userPositionServiceImpl.updatePosition(position);
		if (positionId <= 0) {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("坐标拾取失败");
			return response;
		}
		// }
		return response;
	}

	public BaseResponse<InitResponseAppData> InitAppData(baseRequest<?> request) {
		BaseResponse<InitResponseAppData> response = new BaseResponse<InitResponseAppData>();
		InitResponseAppData appData = new InitResponseAppData();
		// List<LabletType> labletTypes = GetLableTypeAll();
		// LablesResponse lables =
		// businessUtils.LableEntityToModel(labletTypes);
		// appData.setLables(lables);
		// // 获取城市
		// List<Province> listCity = provinceServiceImpl.selectProvinces();
		// Predicate<Province> contain1 = n -> n.getParentId() == 0;

		// List<CityResponseParent> citys = new ArrayList<CityResponseParent>();

		// 启用java lambda表达式
		// listCity.stream().filter(contain1).forEach(n -> {
		// CityResponseParent cityParent = new CityResponseParent();
		// cityParent.setId(n.getProvinceId());
		// cityParent.setName(n.getName());
		//
		// List<CityResponseChild> childs = new ArrayList<CityResponseChild>();
		// Predicate<Province> child = c -> c.getParentId() ==
		// n.getProvinceId();
		// listCity.stream().filter(child).forEach(p -> {
		// CityResponseChild citychild = new CityResponseChild();
		// citychild.setId(p.getProvinceId());
		// citychild.setName(p.getName());
		// childs.add(citychild);
		// });
		// cityParent.setCities(childs);
		// citys.add(cityParent);
		// });

		// 返回版本信息
		VersionResponse versionResponseModel = GetAppVersion(request.getDeviceType(), request.getVersionCode());
		// 二次启动页
		PageTwoResponse pageTwoModel = GetPageTwo();
		appData.setVersionData(versionResponseModel);
		appData.setTwoData(pageTwoModel);

		//二维码
		TwoCodeResponse twoCode=new TwoCodeResponse();
		twoCode.setImgUrl(SystemConfig.TwoCodeUrl);
		// appData.setCityData(citys);
		response.setData(appData);
		return response;

	}

	/**
	 * 获取所有标签
	 * 
	 * @return
	 */
	public List<LabletType> GetLableTypeAll() {
		List<LabletType> labletTypes = labletTypeServiceImpl.selectlabletTypeAll();
		return labletTypes;
	}

	/**
	 * 获取版本信息用于提示更新
	 * 
	 * @param appversionService
	 * @param deviceType
	 * @param versionCode
	 * @return
	 */
	public VersionResponse GetAppVersion(int deviceType, int versionCode) {
		VersionResponse versionModel = new VersionResponse();
		Appversion version = appversionServiceImpl.selectVersionByDevice(deviceType);
		if (version != null && version.getVersionCode() > versionCode) {
			// 提示更新
			versionModel.setExistUp(true);
			versionModel.setDownloadUrl(version.getDownloadUrl());
			versionModel.setUpdateDesc(version.getUpdateDescription());
			versionModel.setVersionCode(version.getVersionCode());
			versionModel.setVersionName(version.getVersionName());
		}
		return versionModel;
	}

	/**
	 * 获取二次启动页
	 * 
	 * @return
	 */
	public PageTwoResponse GetPageTwo() {
		PageTwoResponse pageTwoModel = new PageTwoResponse();
		pageTwoModel.setImgUrl(SystemConfig.pageTwo);
		return pageTwoModel;
	}

	/**
	 * 初始化用户信息
	 * 
	 * @param userServiceImpl
	 * @param userId
	 * @return
	 */
	public InitResponse InitUserData(int userId) {
		InitResponse initUser = new InitResponse();
		UserInfoResponse info = new UserInfoResponse();
		List<User> userDatas = userServiceImpl.initUser(userId);
		if (userDatas != null && userDatas.size() > 0) {
			User user = userDatas.get(0);
			UserDatum datum = user.getDatum();

			info.setUserId(userId);
			info.setShowId(user.getId());
			info.setHeadImage(SystemConfig.ImgurlPrefix + user.getHeadImage());
			info.setNickName(user.getNickName());
			info.setSex(datum.getGender());
			info.setAge(datum.getAge());
			// 根据城市Id转换具体城市 TODO:需要优化成缓存
			// prv
			// new CityRedisManager().GetCitySingle(datum.getCity());
			info.setCityId(datum.getCityId());
			info.setCityName(userServiceImpl.SelectProvincesById(info.getCityId()).getName());
			info.setSign(datum.getSign());
			info.setWeight(datum.getWeight());
			info.setHeight(datum.getHeight());
			info.setShape(datum.getShape());
			info.setSexuat(datum.getSexuat());
			info.setVip(user.getUserLevel());
			info.setInviteCode(user.getInviteCode());
			info.setFull(true);
			info.setEaseId(userId + SystemConfig.EnvIdentity);
			info.setPhone(user.getPhone());
		} else {
			User user = userServiceImpl.selectUserByUserId(userId);
			if (user != null) {
				info.setUserId(userId);
				info.setShowId(user.getId());
				info.setNickName(user.getNickName());
				info.setVip(user.getUserLevel());
				info.setFull(false);
				info.setEaseId(userId + SystemConfig.EnvIdentity);
				info.setPhone(user.getPhone());
			}
		}
		initUser.setUser(info);
		return initUser;
	}

	public BaseResponse<?> InitRedis(baseRequest<InitRedisRequest> request) {
		BaseResponse<?> response = new BaseResponse<Object>();
		InitRedisRequest body = request.getbody();
		if (body.getCatchType() == 1) {
			// 获取城市
			List<Province> listCity = provinceServiceImpl.selectProvinces();
			CityRedisManager manager = new CityRedisManager();
			ResultModel result = manager.SetCity(listCity);
			if (result.isSuccess()) {
				response.setMsg("城市初始化成功");
			} else {
				response.setMsg(result.getMsg());
			}
			// for (Province province : manager.GetCityAll()) {
			// System.out.println(province.getName());
			// }
			response.setMsg(manager.GetCitySingle(400).getName());
		}
		return response;
	}

}
