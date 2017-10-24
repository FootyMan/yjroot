package com.api.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.request.baseRequest;
import com.api.response.BrowesResponse;
import com.api.response.HomeResponse;
import com.api.response.InitResponseAppData;
import com.api.response.LableResponse;
import com.api.response.LableTypeResponse;
import com.api.response.UserImgResponse;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.service.api.impl.UserImgServiceImpl;
import com.service.api.impl.UserPositionServiceImpl;
import com.service.bean.LabletType;
import com.service.bean.User;
import com.service.bean.UserImg;
import com.service.bean.UserPosition;
import com.service.utils.SystemConfig;

@Service("BusinessUtils")
public class BusinessUtils {

	@Autowired
	private UserPositionServiceImpl userPositionService;
	@Autowired
	private UserImgServiceImpl userImgServiceImpl;

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
		return model;
	}
	/**
	 * 最近访客实体对象转换model
	 * 
	 * @param user
	 * @return
	 */
	public BrowesResponse BrowesEntityToModel(User user) {
		BrowesResponse model = new BrowesResponse();
		model.setUserId(user.getUserId());
		model.setNickName(user.getNickName());
		model.setVip(user.getUserLevel());
		model.setHeadImage(SystemConfig.ImgurlPrefix + user.getHeadImage());
		model.setSex(user.getDatum().getGender());
		model.setAge(user.getDatum().getAge());
		model.setSign(user.getDatum().getSign());
		// 访问时间设置
		if (user.getBrowse() != null && user.getBrowse().getBrowseDate() != null) {
			model.setBrowseData(ResponseUtils.GetBrowseTime(user.getBrowse().getBrowseDate()));
			model.setBrowseId(user.getBrowse().getKeyId());
		}
		return model;
	}

	/**
	 * 对象转换Model
	 * 
	 * @param labletTypes
	 * @return
	 */
	// public LablesResponse LableEntityToModel(List<LabletType> labletTypes) {
	// LablesResponse lables=new LablesResponse();
	//
	//// InitResponseAppData appData = new InitResponseAppData();
	// for (LabletType labletType : labletTypes) {
	// LableResponse lableModel = new LableResponse();
	// lableModel.setLableId(labletType.getLableId());
	// lableModel.setLableName(labletType.getLableName());
	// // 1个性类
	// if (labletType.getLableType() == 1) {
	// lableModel.setLableType(labletType.getLableType());
	// lables.getPersonality().add(lableModel);
	// }
	// // 2运动类
	// if (labletType.getLableType() == 2) {
	// lableModel.setLableType(labletType.getLableType());
	// lables.getSports().add(lableModel);
	// }
	// }
	//// appData.setLables(lables);
	// return lables;
	// }

	/**
	 * 根据userId获取图片
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserImgResponse> GetImgListByUserId(int userId) {
		List<UserImgResponse> imgList = new ArrayList<UserImgResponse>();
		List<UserImg> userImgs = userImgServiceImpl.selectImgtByUserId(userId);
		if (userImgs != null && userImgs.size() > 0) {
			for (UserImg userImg : userImgs) {
				UserImgResponse img = new UserImgResponse();
				img.setImg(SystemConfig.ImgurlPrefix + userImg.getImagePath());
				img.setImgId(userImg.getImgId());
				imgList.add(img);
			}
		}
		return imgList;
	}
}
