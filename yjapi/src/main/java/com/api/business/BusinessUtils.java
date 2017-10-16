package com.api.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.request.baseRequest;
import com.api.response.HomeResponse;
import com.api.response.InitResponseAppData;
import com.api.response.LableResponse;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.myErp.manager.bean.LabletType;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserPosition;
import com.myErp.utils.SystemConfig;
import com.myErp.impl.UserPositionServiceImpl;
@Service("BusinessUtils")
public class BusinessUtils {

	@Autowired
	private UserPositionServiceImpl userPositionService;

	/**
	 * 开启线程去添加用户坐标
	 * 
	 * @param userPositionService
	 * @param request
	 */
	public void AddUserPoint(baseRequest request, int userId) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				UserPosition position = new UserPosition();
				position.setIsPosition(1);
				if (request.getLon() <= 0) {
					request.setLon(ResultEnum.defaultLon);
				}
				if (request.getLat() <= 0) {
					request.setLat(ResultEnum.defaultLat);
					position.setIsPosition(0);
				}
				position.setUserId(userId);
				position.setLongitude(request.getLon());
				position.setLatitude(request.getLat());
				userPositionService.insertPosition(position);
			}
		});
		t.start();
	}
	
	/**
	 * 实体对象转换model
	 * 
	 * @param user
	 * @return
	 */
	public HomeResponse EntityToModel(User user) {
		HomeResponse model = new HomeResponse();
		model.setUserId(user.getUserId());
		model.setNickName(user.getNickName());
		model.setVip(user.getUserLevel());
		model.setHeadImage(SystemConfig.ImgurlPrefix + user.getHeadImage());
		model.setSex(user.getDatum().getGender());
		model.setAge(user.getDatum().getAge());
		model.setSign(user.getDatum().getSign());
		// 访问时间设置
		if (user.getBrowseDate() != null) {
			model.setBrowseData(ResponseUtils.GetBrowseTime(user.getBrowseDate()));
		}
		return model;
	}
	/**
	 * 对象转换Model
	 * 
	 * @param labletTypes
	 * @return
	 */
	public InitResponseAppData LableEntityToModel(List<LabletType> labletTypes) {
		InitResponseAppData appData = new InitResponseAppData();
		for (LabletType labletType : labletTypes) {
			LableResponse lableModel = new LableResponse();
			lableModel.setLableId(labletType.getLableId());
			lableModel.setLableName(labletType.getLableName());
			// 1个性类
			if (labletType.getLableType() == 1) {
				lableModel.setLableType(labletType.getLableType());
				appData.getPersonality().add(lableModel);
			}
			// 2运动类
			if (labletType.getLableType() == 2) {
				lableModel.setLableType(labletType.getLableType());
				appData.getSports().add(lableModel);
			}
			// 3音乐类
			if (labletType.getLableType() == 3) {
				lableModel.setLableType(labletType.getLableType());
				appData.getMusic().add(lableModel);
			}
			// 4美食类
			if (labletType.getLableType() == 4) {
				lableModel.setLableType(labletType.getLableType());
				appData.getCate().add(lableModel);
			}
			// 5旅游类
			if (labletType.getLableType() == 5) {
				lableModel.setLableType(labletType.getLableType());
				appData.getTour().add(lableModel);
			}
		}
		return appData;
	}
}
