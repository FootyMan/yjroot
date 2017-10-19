package com.service.api.dao;

import java.util.HashMap;
import java.util.List;

import com.service.bean.Employee;

public abstract interface EmployeeMapper {
	public abstract Employee selectEmployeeOneByKey(HashMap paramHashMap);

	public abstract int insertEmployee(Employee paramEmployee);

	public abstract int updateEmployeeByID(Employee paramEmployee);

	public abstract List<Employee> selectEmployeeListByPage(Employee paramEmployee);
}
