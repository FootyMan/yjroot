package com.myErp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.UserPositionMapper;
import com.myErp.manager.bean.UserPosition;
import com.myErp.utils.DBContextHolder;

@Service("UserPosition")
public class UserPositionServiceImpl {

	@Autowired
	private UserPositionMapper UserPosition;

	/**
	 * 添加
	 * @param position
	 * @return
	 */
	public int insertPosition(UserPosition position)
	{
		DBContextHolder.setDBType("siteRead");
		return UserPosition.insertPosition(position);
	}

	/**
	 * 更新
	 * @param position
	 * @return
	 */
	public int updatePosition(UserPosition position)
	{
		DBContextHolder.setDBType("siteRead");
		return UserPosition.updatePosition(position);
	}
	/**
	 * 是否存储
	 * @param position
	 * @return
	 */
	public int selectCountByUserId(int userid)
	{
		DBContextHolder.setDBType("siteRead");
		return UserPosition.selectCountByUserId(userid);
	}
}
