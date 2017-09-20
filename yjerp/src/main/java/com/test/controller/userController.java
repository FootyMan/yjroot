package com.test.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.myErp.impl.EmployeeServiceImpl;
import com.myErp.manager.bean.Employee;
import com.test.controller.hr.jsonTest;

@Controller
@RequestMapping(value = "/homeAdvert")
public class userController {

	@Autowired
	// private userService userser;
	private EmployeeServiceImpl employe;
    
	@RequestMapping(value = "/getAdvertByID", method = { RequestMethod.POST, RequestMethod.GET })
	public void getAdvertByID(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Employee emp = new Employee();
		emp.setRoleId(0);
		emp.setQuitDate(new Date());
		emp.setEntryDate(new Date());
		emp.setEmpWx("empWx");
		emp.setEmpStatus("empStatus");
		emp.setEmpQq("empQq");
		emp.setEmpPwd("empPwd");
		emp.setEmpPhone("empPhone");
		emp.setEmpNo("empNo");
		emp.setEmpName("empName");
		emp.setEmpLogin("empLogin");
		emp.setEmpGender("empGender");
		emp.setEmpEmail("empEmail");
		emp.setDeptCode("deptCode");
		emp.setCityCode("cityCode");
		emp.setAddDate(new Date());
		employe.insertEmployee(emp);

		// List<user> sd = userser.queryInfo();
		// for (user u : sd) {
		// System.out.println("濮撳悕锛�" + u.getUsername() + "鎬у埆锛�" + u.getSex());
		// }
		// user u=new user();
		// u.setUsername("棣栨苯绉熻溅");
		// u.setSex(1);
		// userser.saveInfo(u);
		// System.out.println("娣诲姞鎴愬姛 杩斿洖涓婚敭"+u.getId());

	}

	/**
	 * 
	 * @param enp
	 */
	@RequestMapping(value = "/jsonPost", method = RequestMethod.POST)
	public void jsonPost(@RequestBody jsonTest enp) {
		// String str = enp.getEmpName();
		System.out.print(enp.getName());
	}
	/**
	 * @author HCY
	 * @RequestBody 鏃犳硶鐢╣et璇锋眰浣�
	 */
	@RequestMapping(value = "/jsonGet", method = { RequestMethod.POST, RequestMethod.GET })
	public void jsonGet(@RequestBody String enp) {
		System.out.print(enp);
	}
}
