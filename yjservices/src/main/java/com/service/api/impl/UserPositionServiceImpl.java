package com.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.UserPositionMapper;
import com.service.bean.UserPosition;
import com.service.utils.DBContextHolder;

@Service("UserPosition")
public class UserPositionServiceImpl {

	@Autowired
	private UserPositionMapper UserPosition;

	/**
	 * 添加
	 * 
	 * @param position
	 * @return
	 */
	public int insertPosition(UserPosition position) {
		DBContextHolder.setDBType("siteRead");
		return UserPosition.insertPosition(position);
	}

	/**
	 * 更新
	 * 
	 * @param position
	 * @return
	 */
	public int updatePosition(UserPosition position) {
		DBContextHolder.setDBType("siteRead");
		int count = UserPosition.selectCountByUserId(position.getUserId());
		if (count > 0) {
			return UserPosition.updatePosition(position);
		} else {
			return UserPosition.insertPosition(position);
		}

	}

	/**
	 * 是否存储
	 * 
	 * @param position
	 * @return
	 */
	public int selectCountByUserId(int userid) {
		DBContextHolder.setDBType("siteRead");
		return UserPosition.selectCountByUserId(userid);
	}
}
