package com.service.api.dao;

import com.service.bean.LoginLog;

public abstract interface LoginLogMapper {

	public abstract int insertLoginLog(LoginLog log);
}
