package com.service.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.api.dao.LoginLogMapper;
import com.service.bean.LoginLog;
import com.service.utils.DBContextHolder;

@Service("loginLogServiceImpl")
public class LoginLogServiceImpl {

	@Autowired
	private LoginLogMapper loginLogMapper;

	public int insertLoginLog(LoginLog log) {
		DBContextHolder.setDBType("siteRead");
		return loginLogMapper.insertLoginLog(log);
	}
}
