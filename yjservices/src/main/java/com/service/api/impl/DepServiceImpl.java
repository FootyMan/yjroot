package com.service.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.service.api.dao.DepMapper;
import com.service.bean.Dept;
import com.service.utils.DBContextHolder;

@Service("depService")
public class DepServiceImpl {
	// extends SqlSessionDaoSupport implements DepMapper
	 @Autowired
	private DepMapper dep;

	public int insertDep(Dept paramdep) {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("siteRead");
		return dep.insertDep(paramdep);
	}

	public List<Dept> selectDepListByPage(Dept paramdep) {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("siteRead");
		return dep.selectDepListByPage(paramdep);
	}

	public List<Dept> getdepAll() {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("siteRead");
		return dep.getdepAll();
	}

	public Map<Integer, String> getMapDepById() {
		Map<Integer, String> returnMap = new HashMap<Integer, String>();
		List<Dept> map = getdepAll();
		for (Dept map2 : map) {
			returnMap.put(map2.getDeptId(), map2.getDeptName());
		}
		return returnMap;
	}

}
