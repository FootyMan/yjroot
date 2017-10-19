package com.erp.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.erp.model.UserModel;
import com.service.api.impl.ProvinceServiceImpl;
import com.service.api.impl.UserServiceImpl;
import com.service.bean.Employee;
import com.service.bean.User;
import com.service.bean.UserDatum;
import com.service.enums.DeviceType;
import com.service.enums.UserLevel;
import com.service.erp.impl.UserServiceImplERP;
import com.service.utils.Pagination;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserServiceImplERP userServiceImplERP;
	@Autowired
	private ProvinceServiceImpl provinceServiceImpl;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(UserModel userModel, Model model) {// Employee
																// employee,
		Pagination pagination = userModel.getPage();
		if (pagination == null) {
			pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		User user = new User();
		List<User> empReslist = userServiceImplERP.GetUserList(user);
		List<UserModel> listUser = new ArrayList<UserModel>();
		for (User x : empReslist) {
			UserModel m = new UserModel();
			UserDatum datm = x.getDatum();
			m.setUserId(x.getUserId());
			m.setUserNo(String.valueOf(x.getUserId()));
			m.setPhone(x.getPhone());
			m.setDeviceType(DeviceType.getDeviceTypeByCode(x.getDeviceType()).getDesc());
			m.setNickName(x.getNickName());
			m.setRegisterTime(x.getRegisterTime());
			m.setUserLevel(UserLevel.getOrderStateByCode(x.getUserLevel()).getDesc());
			m.setIsDisable(x.getIsDisable() == 1 ? "启用" : "禁用");
			m.setInviteCode(x.getInviteCode());
			m.setAge(datm.getAge());
			m.setSex(datm.getGender() == 1 ? "男" : "女");
			m.setCityName(provinceServiceImpl.SelectProvincesById(datm.getCityId()).getName());
			m.setSexuat(datm.getSexuat());
			listUser.add(m);
		}
		// Map<Integer, String> roleMap = roleService.getMapRoleById();
		// System.out.println(roleMap.get(1));
		// System.out.println(roleMap.get(2));

		// model.addAttribute("roleMap", roleMap);
		// Map<Integer, String> map = new HashMap<Integer, String>();
		// map.put(1, "1");
		// map.put(2, "33");

		model.addAttribute("listUser", listUser);
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/user/list");
	}
	
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView add(Model model) {// Employee
																// employee,
		 
		return new ModelAndView("/user/add");
	}
	
	
	/**
	 * 首页用户列表
	 * @param userModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView Home(UserModel userModel, Model model) {// Employee
																// employee,
		Pagination pagination = userModel.getPage();
		if (pagination == null) {
			pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		User user = new User();
		List<User> empReslist = userServiceImplERP.GetHomeUserList(user);
		List<UserModel> listUser = new ArrayList<UserModel>();
		for (User x : empReslist) {
			UserModel m = new UserModel();
			UserDatum datm = x.getDatum();
			m.setUserId(x.getUserId());
			m.setUserNo(String.valueOf(x.getUserId()));
			m.setPhone(x.getPhone());
			m.setDeviceType(DeviceType.getDeviceTypeByCode(x.getDeviceType()).getDesc());
			m.setNickName(x.getNickName());
			m.setRegisterTime(x.getRegisterTime());
			m.setUserLevel(UserLevel.getOrderStateByCode(x.getUserLevel()).getDesc());
			m.setIsDisable(x.getIsDisable() == 1 ? "启用" : "禁用");
			m.setInviteCode(x.getInviteCode());
			m.setAge(datm.getAge());
			m.setSex(datm.getGender() == 1 ? "男" : "女");
			m.setCityName(provinceServiceImpl.SelectProvincesById(datm.getCityId()).getName());
			m.setSexuat(datm.getSexuat());
			listUser.add(m);
		}

		model.addAttribute("listUser", listUser);
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/user/home");
	}
}
