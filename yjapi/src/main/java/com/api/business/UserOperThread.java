package com.api.business;

import com.api.request.baseRequest;
import com.service.api.impl.UserBrowseExtServiceImpl;
import com.service.api.impl.UserInviteServiceImpl;
import com.service.api.impl.UserServiceImpl;
import com.service.bean.User;
import com.service.bean.UserBrowseExt;
import com.service.bean.UserInvite;
import com.service.easemob.NeteaseBusiness;
import com.service.easemob.NeteaseModel;
import com.service.utils.SystemConfig;

public class UserOperThread implements Runnable {

 
	private UserInviteServiceImpl userInviteServiceImpl;
	 
	private BusinessUtils businessUtils;
	 
	private UserBrowseExtServiceImpl userBrowseExtServiceImpl;
	 
	private UserServiceImpl userServiceImpl;
	
	private baseRequest<?> request;
	private User invitation;
	private int registerId;
	

	public UserOperThread(baseRequest<?> request, User invitation, int registerId,UserInviteServiceImpl userInviteServiceImpl,
			BusinessUtils businessUtils, UserBrowseExtServiceImpl userBrowseExtServiceImpl,UserServiceImpl userServiceImpl) {
		this.request = request;
		this.invitation = invitation;
		this.registerId = registerId;
		this.userInviteServiceImpl=userInviteServiceImpl;
		this.businessUtils=businessUtils;
		this.userBrowseExtServiceImpl=userBrowseExtServiceImpl;
		this.userServiceImpl=userServiceImpl;
	}

	@Override
	public void run() {
	 
		// 添加邀请
		UserInvite invite = new UserInvite();
		invite.setInviteUserId(invitation.getUserId());
		invite.setInviteCode(invitation.getInviteCode());
		invite.setRegisterUserId(registerId);
		userInviteServiceImpl.insertInvite(invite);
		// 添加经纬度
		businessUtils.AddUserPoint(request, registerId);
		// 添加浏览主表 用于首页查询排序、统计浏览次数
		UserBrowseExt ext = new UserBrowseExt();
		ext.setUserId(registerId);
		ext.setBrowseNumber(0);
		userBrowseExtServiceImpl.insertBrowseExt(ext);
		// 注册环信----改用网易云im
		String easemobId = registerId + SystemConfig.EaseSuffixId;
		// String result = EaseMobBusiness.AccountCreate(easemobId);
		// Map map = (Map) JSON.parse(result);
		NeteaseModel netease = NeteaseBusiness.CreateaccId(easemobId, registerId);
		// if (map != null && !map.containsKey("error")) {
		if (netease != null && netease.getCode() == 200) {
			// 更新用户
			User upUser = new User();
			upUser.setUserId(registerId);
			upUser.setEasemobId(easemobId);
			upUser.setImToken(netease.getInfo().getToken());
			upUser.setIsEasemob(1);
			userServiceImpl.updateUser(upUser);

		}

	}
}
