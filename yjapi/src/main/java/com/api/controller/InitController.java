package com.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.api.business.UserBusiness;
import com.api.request.*;
import com.api.response.*;
import com.api.utils.decrypt.ResponseEncryptBody;
import com.myErp.impl.AppversionServiceImpl;
import com.myErp.impl.LabletTypeServiceImpl;
import com.myErp.impl.ProvinceServiceImpl;
import com.myErp.impl.UserPositionServiceImpl;
import com.myErp.impl.UserServiceImpl;
import com.myErp.manager.bean.LabletType;
import com.myErp.manager.bean.Province;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserDatum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/init", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "初始化接口")
public class InitController {

	@Autowired
	private UserPositionServiceImpl UserPositionService;
	@Autowired
	private AppversionServiceImpl appversionService;
	@Autowired
	private LabletTypeServiceImpl labletTypeServiceImpl;
	@Autowired
	private ProvinceServiceImpl provinceServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;
	// @Autowired
	// private InvitationCodeServiceImpl invitationCodeServiceImpl;

	/**
	 * 初始化用户信息 1、登录和注册之后 必须调用此接口（方便获取用户位置） 2、如果已登录 打开APP先调用此接口 传入经纬度
	 * 3、此接口返回用户偏好设置 4返回用户提示更新 5二次启动页
	 * 
	 * @param input
	 * @return
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/initUser", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-initUser", value = "初始化用户"
			+ "1、登录和注册之后 必须调用此接口（方便获取用户位置）2、如果已登录 打开APP先调用此接口 传入经纬度 3、此接口返回用户偏好设置", notes = "初始化用户")
	public baseResponse<InitResponse> initUser(
			@ApiParam(value = "输入") @RequestBody baseRequest<InitUserRequest> request) throws Exception {
		// for (int i = 0; i < 100000; i++) {
		// String code = StringUtils.getRandomNum(999999, 111111);
		// InvitationCode invitation =
		// invitationCodeServiceImpl.selectCode(code);
		// if (invitation == null) {
		// InvitationCode entity = new InvitationCode();
		// entity.setCode(code);
		// entity.setIsValid(1);
		// invitationCodeServiceImpl.insertCode(entity);
		// }
		// }
		baseResponse<InitResponse> response = UserBusiness.getInstance().initUser(UserPositionService, request);
		InitResponse initUser = UserBusiness.getInstance().InitUserData(userServiceImpl, request.getUserId());
		response.setData(initUser);
		return response;
	}

	/**
	 * 初始化app填充的数据 如城市、我的标签 角色
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/appData", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-user", value = "初始化app填充的数据 如城市、我的标签 角色4返回用户提示更新 5二次启动页", notes = "初始化app填充的数据")
	public baseResponse<InitResponseAppData> InitAppData(@ApiParam(value = "输入") @RequestBody baseRequest request)
			throws Exception {
		baseResponse<InitResponseAppData> response = new baseResponse<InitResponseAppData>();
		List<LabletType> labletTypes = labletTypeServiceImpl.selectlabletTypeAll();
		InitResponseAppData appData = UserBusiness.getInstance().LableEntityToModel(labletTypes);
		// 获取城市
		List<Province> listCity = provinceServiceImpl.selectProvinces();
		Predicate<Province> contain1 = n -> n.getParentId() == 0;

		List<CityResponseParent> citys = new ArrayList<CityResponseParent>();

		// 启用java lambda表达式
		listCity.stream().filter(contain1).forEach(n -> {
			CityResponseParent cityParent = new CityResponseParent();
			cityParent.setId(n.getProvinceId());
			cityParent.setName(n.getName());

			List<CityResponseChild> childs = new ArrayList<CityResponseChild>();
			Predicate<Province> child = c -> c.getParentId() == n.getProvinceId();
			listCity.stream().filter(child).forEach(p -> {
				CityResponseChild citychild = new CityResponseChild();
				citychild.setId(p.getProvinceId());
				citychild.setName(p.getName());
				childs.add(citychild);
			});
			cityParent.setCities(childs);
			citys.add(cityParent);
		});

		// 返回版本信息
		VersionResponse versionResponseModel = UserBusiness.getInstance().GetAppVersion(appversionService,
				request.getDeviceType(), request.getVersionCode());
		// 二次启动页
		PageTwoResponse pageTwoModel = UserBusiness.getInstance().GetPageTwo();
		appData.setVersionData(versionResponseModel);
		appData.setTwoData(pageTwoModel);

		appData.setCityData(citys);
		response.setData(appData);
		return response;
	}
}
