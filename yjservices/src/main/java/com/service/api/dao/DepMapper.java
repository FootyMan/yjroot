package com.service.api.dao;

import java.util.List;

import com.service.bean.Dept;

public abstract interface DepMapper {

	public abstract int insertDep(Dept paramdep);

	public abstract List<Dept> selectDepListByPage(Dept paramdep);

	public abstract List<Dept> getdepAll();
}
