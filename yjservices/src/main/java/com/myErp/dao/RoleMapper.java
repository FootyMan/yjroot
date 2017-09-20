package com.myErp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.myErp.manager.bean.Role;

public abstract interface RoleMapper {

	public abstract int insertRole(Role paramEmployee);

	public abstract List<Role> selectRoleListByPage(Role paramEmployee);

	public abstract List<Role> getAllRoleList();
}
