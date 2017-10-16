package com.erp.controller.hr;

import java.sql.RowIdLifetime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myErp.impl.DepServiceImpl;
import com.myErp.impl.EmployeeServiceImpl;
import com.myErp.impl.RoleServiceImpl;
import com.myErp.manager.bean.Employee;
import com.myErp.manager.bean.Role;
import com.myErp.utils.GlobalsConstant;
import com.myErp.utils.Pagination;
import com.myErp.utils.RequestUtils;

@Controller
@RequestMapping("/hr/employee")
public class EmployeeController {
	@Autowired
	private EmployeeServiceImpl employeeService;
	@Autowired
	private RoleServiceImpl roleService;
	@Autowired
	private DepServiceImpl depService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(Employee employee, Model model) {// Employee
																// employee,
		Pagination pagination = employee.getPage();
		if (pagination == null) {
			pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		List<Employee> empReslist = employeeService.selectEmployeeListByPage(employee);
		Map<Integer, String> roleMap = roleService.getMapRoleById();
		// System.out.println(roleMap.get(1));
		// System.out.println(roleMap.get(2));

		model.addAttribute("roleMap", roleMap);
//		Map<Integer, String> map = new HashMap<Integer, String>();
//		map.put(1, "1");
//		map.put(2, "33");

		model.addAttribute("empList", empReslist);
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/hr/emp/list");
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(Model model) {// Employee employee,
		Map<Integer, String> roleMap = roleService.getMapRoleById();
		Map<Integer, String> depMap = depService.getMapDepById();
		model.addAttribute("roleMap", roleMap);
		model.addAttribute("depMap", depMap);
		return new ModelAndView("/hr/emp/add");
	}

	@RequestMapping(value = "/add.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addDO(Employee employee, Model model) {
		employee.setCityCode("1");
		employee.setEmpStatus("1");
		int res = employeeService.insertEmployee(employee);
		return new ModelAndView("redirect:/hr/employee/list");

	}
	@RequestMapping(value = "/list.detail", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listDetail(HttpServletRequest request, Employee employee, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));//RequestUtils.getSafeInt(request, "id");
		
		HashMap map = new HashMap();
		map.put("key", "id");
		map.put("value", id);
		Employee emp = employeeService.selectEmployeeOneByKey(map);
		model.addAttribute("emp", emp);
		return new ModelAndView("/hr/emp/detail");
	}
}
