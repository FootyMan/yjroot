package com.myErp.dao;

import java.util.List;

import com.myErp.manager.bean.Dept;

public abstract interface DepMapper {

	public abstract int insertDep(Dept paramdep);

	public abstract List<Dept> selectDepListByPage(Dept paramdep);

	public abstract List<Dept> getdepAll();
}
