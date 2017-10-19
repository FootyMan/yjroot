package com.service.api.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.service.bean.Role;

public abstract interface RoleMapper {

	public abstract int insertRole(Role paramEmployee);

	public abstract List<Role> selectRoleListByPage(Role paramEmployee);

	public abstract List<Role> getAllRoleList();
}
