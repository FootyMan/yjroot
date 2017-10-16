package com.erp.controller.hr;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myErp.impl.DepServiceImpl;
import com.myErp.impl.EmployeeServiceImpl;
import com.myErp.impl.RoleServiceImpl;
import com.myErp.manager.bean.Employee;
import com.myErp.manager.bean.Role;
import com.myErp.utils.Pagination;

@Controller
@RequestMapping("/hr/role")
public class RoleController {

//	@Autowired
//	private RoleServiceImpl roleService;
//	@Autowired
//	private DepServiceImpl depService;
//
//	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView list(Role role, Model model) {// Employee
//																// employee,
//		Pagination pagination = role.getPage();
//		if (pagination == null) {
//			pagination = new Pagination();
//		}
//		Pagination.threadLocal.set(pagination);
//		List<Role> roleReslist = roleService.selectRoleListByPage(role);
//		Map<Integer, String> depMap = depService.getMapDepById();
//		model.addAttribute("roleReslist", roleReslist);
//		model.addAttribute("depMap", depMap);
//		model.addAttribute("page", Pagination.threadLocal.get());
//		return new ModelAndView("/hr/role/list");
//	}
//
//	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView add(Model model) {// Employee employee,
//		Map<Integer, String> depMap = depService.getMapDepById();
//		model.addAttribute("depMap", depMap);
//		return new ModelAndView("/hr/role/add");
//	}
//
//	@RequestMapping(value = "/add.do", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView addDO(Role role, Model model) {
//		roleService.insertRole(role);
//		return new ModelAndView("redirect:/hr/role/list");
//
//	}
}
