package com.erp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.aspect.Log;
import com.erp.impl.HomeMemberImpl;
import com.erp.model.UserModel;
import com.erp.service.MemberService;
import com.erp.utils.PageUtils;
import com.erp.utils.Query;
import com.erp.utils.R;
import com.service.bean.User;
import com.service.bean.UserDatum;
import com.service.enums.UserLevel;
import com.service.utils.SystemConfig;

@RequestMapping("/erp/homeuser")
@Controller
public class HomeUserController extends BaseController {
	private String prefix = "erp/home";
	@Autowired
	MemberService userServiceERP;
	@Autowired
	HomeMemberImpl homeUserServiceImplERP;
//	@Autowired
//	PropertiesSystemConfig systemConfig;

	@RequiresPermissions("erp:home:list")
	@GetMapping("")
	String home(Model model) {
		return prefix + "/list";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<User> sysUserList = userServiceERP.GetHomeUserList(query);
		List<UserModel> listUser = new ArrayList<UserModel>();
		for (User x : sysUserList) {
			UserModel m = new UserModel();
			UserDatum datm = x.getDatum();
			m.setUserId(x.getUserId());
			m.setUserNo(x.getUserNo());
			m.setPhone(x.getPhone());
			m.setNickName(x.getNickName());
			m.setRegisterTime(x.getRegisterTime());
			m.setUserLevel(UserLevel.getOrderStateByCode(x.getUserLevel()).getDesc());
			m.setIsDisable(x.getIsDisable());
			m.setInviteCode(x.getInviteCode());
			m.setAge(datm.getAge());
			m.setSex(datm.getGender());
			m.setSexuat(datm.getSexuat());
			m.setHeadImage(SystemConfig.ImgurlPrefix+ x.getHeadImage());
			listUser.add(m);
		}
		int total = userServiceERP.GetHomeUserCount(query);
		PageUtils pageUtil = new PageUtils(listUser, total);
		return pageUtil;
	}

	@RequiresPermissions("erp:homoe:remove")
	@Log("删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") int[] userIds) {
		if (homeUserServiceImplERP.deleteHomeUser(userIds) > 0) {
			return R.ok();
		}
		return R.error();
	}

}
