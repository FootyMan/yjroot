package com.myErp.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myErp.dao.RoleMapper;
import com.myErp.manager.bean.Role;
import com.myErp.utils.DBContextHolder;

@Service("roleService")
public class RoleServiceImpl {

	//extends SqlSessionDaoSupport 
	@Autowired
	private RoleMapper emp;

	public int insertRole(Role paramRole) {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("siteRead");
		return emp.insertRole(paramRole);
	}

	public List<Role> selectRoleListByPage(Role paramRole) {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("siteRead");
		return emp.selectRoleListByPage(paramRole);
	}

	public List<Role> getAllRoleList() {
		DBContextHolder.setDBType("siteRead");
		return emp.getAllRoleList();
	}

	public Map<Integer, String> getMapRoleById() {
		Map<Integer, String> returnMap=new HashMap<Integer,String>();
		List<Role> map = getAllRoleList();
		for (Role map2 : map) {
			returnMap.put(map2.getRoleId(), map2.getRoleName());
		}
		return returnMap;
	}

}
