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
import com.myErp.enums.DeviceType;
import com.myErp.enums.UserLevel;
import com.myErp.impl.ProvinceServiceImpl;
import com.myErp.impl.UserServiceImpl;
import com.myErp.manager.bean.Employee;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserDatum;
import com.myErp.utils.Pagination;
import com.zhzy.erp.impl.UserServiceImplERP;

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
			m.setId(x.getUserId());
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
}
