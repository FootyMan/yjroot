package com.erp.controller;

 
import java.util.HashMap;
 

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.service.api.impl.EmployeeServiceImpl;
import com.service.bean.Employee;
import com.service.utils.GlobalsConstant;

@Controller
public class AccountsController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@RequestMapping(value = "/accounts/login", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView login(@RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd,
			Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// request.setCharacterEncoding("utf-8");
		// response.setContentType("text/html;charset=utf-8");
		HashMap<String, String> loginMap = new HashMap<String, String>();
		loginMap.put("key", "empLogin");
		loginMap.put("value", userName);
		loginMap.put("userPwd", userPwd);
		Employee emp = employeeService.selectEmployeeByLogin(loginMap);
		if (emp != null) {
			return new ModelAndView("redirect:" + GlobalsConstant.MAIN_HOME);
		} else {
			ModelAndView view = new ModelAndView("redirect:/index.jsp");
			view.addObject("error", ("用户名密码错误"));// ,EncodingTool.encodeStr
			return view;
		}
		// Cookie cookie = CookieUtil.getCookieByName(request, "Ticket");
		// if (cookie != null) {
		// System.out.println(cookie.getValue());
		// }
		// if (cookie == null) {
		// // 鍏堝垹闄�
		// CookieUtil.addCookie(response, "Ticket", userName, 0);
		// //System.out.println("Cookie" + CookieUtil.getCookieByName(request,
		// "Ticket").getValue());
		// }

	}

	@RequestMapping(value = "/accounts/home", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView home(Model model) {
		return new ModelAndView("/home");
	}

//	@RequestMapping(value = "/list.detail", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView listDetail(HttpServletRequest request, Employee employee, Model model) {
//		int id = RequestUtils.getSafeInt(request, "id");
//		HashMap map = new HashMap();
//		map.put("key", "id");
//		map.put("value", id);
//		Employee emp = employeeService.selectEmployeeOneByKey(map);
//		model.addAttribute("emp", emp);
//		return new ModelAndView("/hr/emp/detail");
//	}
}
