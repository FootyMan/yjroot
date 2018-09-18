package com.erp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.aspect.Log;
import com.erp.impl.MemberFinancialmpl;
import com.erp.model.ProfitListModel;
import com.erp.utils.PageUtils;
import com.erp.utils.R;
import com.service.bean.User;
import com.service.bean.UserFinancial;
import com.service.bean.UserFinancialDetail;
import com.service.enums.FinancialOperateStatus;
import com.service.utils.DateUtil;

@RequestMapping("/erp/financia")
@Controller
public class MemberFinanciaController extends BaseController {
	private String prefix = "erp/financia";
	@Autowired
	MemberFinancialmpl financialmpl;

	@RequiresPermissions("erp:financia:list")
	@GetMapping("")
	String financia(Model model) {
		return prefix + "/list";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {

		List<ProfitListModel> wdList = new ArrayList<ProfitListModel>();
		List<UserFinancial> listData = financialmpl.list(params);
		for (UserFinancial f : listData) {
			ProfitListModel m = new ProfitListModel();
			User user = f.getUser();
			UserFinancialDetail detail = f.getFinancialDetail();
			m.setRecordID(detail.getRecordID());
			m.setUserid(user.getUserId());
			m.setUserNo(user.getUserNo());
			m.setNickName(user.getNickName());
			m.setFinancialMoney(detail.getFinancialMoney());
			m.setPayAccount(f.getPayAccount());
			m.setPayRealName(f.getPayRealName());
			m.setTotalMoney(f.getTotalMoney());
			m.setSourceNumber(detail.getSourceNumber());
			m.setOperateDate(DateUtil.getDateTime(detail.getOperateDate()));
			m.setOperateStatusName(
					FinancialOperateStatus.getFinancialOperateStatusByCode(detail.getOperateStatus()).getDesc());
			m.setOperateStatus(detail.getOperateStatus());
			wdList.add(m);
		}
		int total = financialmpl.count(params);
		PageUtils pageUtil = new PageUtils(wdList, total);
		return pageUtil;
	}

	/**
	 * 设置已打款
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("erp:financia:set")
	@Log("编辑用户")
	@GetMapping("/set/{id}")
	@ResponseBody
	R edit(@PathVariable("id") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("recordID", id);
		map.put("operateStatus", 2);
		int result = financialmpl.updateOperateStatus(map);
		if (result > 0) {
			return R.ok();
		}
		return R.error();
	}
}
