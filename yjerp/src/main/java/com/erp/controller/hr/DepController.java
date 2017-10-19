package com.erp.controller.hr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.service.api.impl.DepServiceImpl;
import com.service.bean.Dept;
import com.service.utils.Pagination;


@Controller
@RequestMapping("/hr/dep")
public class DepController {

//	@Autowired
//	private DepServiceImpl depService;
//	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView list(Dept dep, Model model) {// Employee
//																// employee,
//		Pagination pagination = dep.getPage();
//		if (pagination == null) {
//			pagination = new Pagination();
//		}
//		Pagination.threadLocal.set(pagination);
//		List<Dept> depReslist = depService.selectDepListByPage(dep);
//		model.addAttribute("depReslist", depReslist);
//		model.addAttribute("page", Pagination.threadLocal.get());
//		return new ModelAndView("/hr/dep/list");
//	}
//	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView add(Model model) {// Employee employee,
//		return new ModelAndView("/hr/dep/add");
//	}
//	@RequestMapping(value = "/add.do", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView addDO(Dept dep, Model model) {
//		depService.insertDep(dep);
//		return new ModelAndView("redirect:/hr/dep/list");
//
//	}
}
