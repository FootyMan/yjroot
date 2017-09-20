package com.myErp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.UserDatumMapper;
import com.myErp.manager.bean.UserDatum;
import com.myErp.utils.DBContextHolder;

@Service("userDatumService")
public class UserDatumServiceImpl {
	@Autowired
	private UserDatumMapper userDatumMapper;

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int insertDatum(UserDatum datum) {
		DBContextHolder.setDBType("siteRead");
		return userDatumMapper.insertDatum(datum);
	}

	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 */
	public int updateDatum(UserDatum datum) {
		DBContextHolder.setDBType("siteRead");
		return userDatumMapper.updateDatum(datum);
	}

	/**
	 * 根据ID获取
	 * 
	 * @param user
	 * @return
	 */
	public UserDatum selectDatumByUserId(int userId) {
		DBContextHolder.setDBType("siteRead");
		return userDatumMapper.selectDatumByUserId(userId);
	}
}
