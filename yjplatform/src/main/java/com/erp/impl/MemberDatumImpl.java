package com.erp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.MemberDatumDao;
import com.service.bean.UserDatum;

@Service
public class MemberDatumImpl {
	@Autowired
	private MemberDatumDao userDatumMapper;

	/**
	 * 添加
	 * 
	 * @param user
	 * @return
	 */
	public int insertDatum(UserDatum datum) {
		return userDatumMapper.insertDatum(datum);
	}

	/**
	 * 更新
	 * 
	 * @param user
	 * @return
	 */
	public int updateDatum(UserDatum datum) {
		return userDatumMapper.updateDatum(datum);
	}

	/**
	 * 根据ID获取
	 * 
	 * @param user
	 * @return
	 */
	public UserDatum selectDatumByUserId(int userId) {
		return userDatumMapper.selectDatumByUserId(userId);
	}
}
