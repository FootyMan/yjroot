package com.test.controller.hr;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myErp.manager.bean.Employee;
import com.myErp.manager.bean.User;
import com.myErp.utils.Pagination;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(Employee employee, Model model) {// Employee
													
		 
//		Pagination pagination = employee.getPage();
//		if (pagination == null) {
//			pagination = new Pagination();
//		}
//		Pagination.threadLocal.set(pagination);
//		List<Employee> empReslist = employeeService.selectEmployeeListByPage(employee);
//		Map<Integer, String> roleMap = roleService.getMapRoleById();
//		// System.out.println(roleMap.get(1));
//		// System.out.println(roleMap.get(2));
//
//		model.addAttribute("roleMap", roleMap);
////		Map<Integer, String> map = new HashMap<Integer, String>();
////		map.put(1, "1");
////		map.put(2, "33");
//
//		model.addAttribute("empList", empReslist);
//		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/hr/emp/list");
	}
}
