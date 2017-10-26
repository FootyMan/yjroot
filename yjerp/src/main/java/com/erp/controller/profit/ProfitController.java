package com.erp.controller.profit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.erp.model.OrderModel;
import com.erp.model.ProfitListModel;
import com.service.bean.Order;
import com.service.bean.OrderListParameter;
import com.service.bean.Product;
import com.service.bean.User;
import com.service.bean.UserFinancial;
import com.service.bean.UserFinancialDetail;
import com.service.enums.DeviceType;
import com.service.enums.FinancialOperateStatus;
import com.service.enums.OrderState;
import com.service.enums.PayType;
import com.service.erp.impl.UserFinancialServiceImplERP;
import com.service.parameter.bean.FinancialParametERP;
import com.service.utils.DateUtil;
import com.service.utils.Pagination;
import com.service.utils.StringUtils;

@Controller
@RequestMapping("/profit")
public class ProfitController {

	@Autowired
	private UserFinancialServiceImplERP userFinancialServiceImplERP;

	/**
	 * 提现列表
	 * 
	 * @param proModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wdList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView WithdrawList(ProfitListModel proModel, Model model) {// Employee

		// OrderListParameter orderParameter = new OrderListParameter();
		Pagination pagination = proModel.getPage();
		if (pagination == null) {
			pagination = new Pagination();
		}

		Pagination.threadLocal.set(pagination);
		FinancialParametERP paramet=new FinancialParametERP();
		if (!StringUtils.isEmpty(proModel.getUserNo())) {
			paramet.setUserNo(proModel.getUserNo());
		}
		if (!StringUtils.isEmpty(proModel.getPhone())) {
			paramet.setPhone(proModel.getPhone());
		}
		if (proModel.getOperateStatus()>0) {
			paramet.setOperateStatus(proModel.getOperateStatus());
		}

		List<ProfitListModel> wdList = new ArrayList<ProfitListModel>();
		List<UserFinancial> listData = userFinancialServiceImplERP.selectWdListByPage(paramet);
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
		model.addAttribute("wdList", wdList);
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/profit/wdList");
	}

	/**
	 * 设置已打款
	 * @param recordId
	 * @return
	 */
	@RequestMapping(value = "/setOperate", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public int SetOperateState(int recordId) {
		UserFinancialDetail detail = new UserFinancialDetail();
		detail.setRecordID(recordId);
		detail.setOperateStatus(2);
		int result = userFinancialServiceImplERP.updateOperateStatus(detail);
		return result;
	}
}
