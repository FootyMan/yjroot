package com.myErp.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.NullLiteral;
import org.aspectj.weaver.ast.Var;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.myErp.dao.EmployeeMapper;
import com.myErp.manager.bean.Employee;
import com.myErp.utils.DBContextHolder;

@Service("employeeService")
public class EmployeeServiceImpl {

	//
	@Autowired
	EmployeeMapper emp;

	public Employee selectEmployeeByLogin(HashMap paramHashMap) {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("siteRead");
		Employee loyee = emp.selectEmployeeOneByKey(paramHashMap);
		if (loyee != null && loyee.getEmpPwd().equals(paramHashMap.get("userPwd"))) {
			return loyee;
		}
		return null;
	}

	public Employee selectEmployeeOneByKey(HashMap paramHashMap) {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("siteRead");
		return emp.selectEmployeeOneByKey(paramHashMap);
	}

	public int insertEmployee(Employee paramEmployee) {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("siteRead");
		return emp.insertEmployee(paramEmployee);
	}

	public int updateEmployeeByID(Employee paramEmployee) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Employee> selectEmployeeListByPage(Employee paramEmployee) {
		// TODO Auto-generated method stub
		DBContextHolder.setDBType("siteRead");
		return emp.selectEmployeeListByPage(paramEmployee);
	}
}
