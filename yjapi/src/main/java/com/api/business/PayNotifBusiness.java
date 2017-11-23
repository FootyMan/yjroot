package com.api.business;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.controller.PayNotifController;
import com.service.api.impl.OrderServiceImpl;
import com.service.api.impl.ProductServiceImpl;
import com.service.api.impl.UserFinancialDetailServiceImpl;
import com.service.api.impl.UserFinancialServiceImpl;
import com.service.api.impl.UserInviteServiceImpl;
import com.service.api.impl.UserRechargeServiceImpl;
import com.service.api.impl.UserServiceImpl;
import com.service.bean.Order;
import com.service.bean.Product;
import com.service.bean.User;
import com.service.bean.UserFinancial;
import com.service.bean.UserFinancialDetail;
import com.service.bean.UserInvite;
import com.service.bean.UserRecharge;
import com.service.enums.FinancialOperateStatus;
import com.service.enums.FinancialType;
import com.service.enums.RechargeValid;
import com.service.utils.DateUtil;
import com.service.utils.SystemConfig;

@Service("PayNotifBusiness")
public class PayNotifBusiness {

	private static Logger logger = Logger.getLogger(PayNotifBusiness.class);
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserRechargeServiceImpl userRechargeServiceImpl;
	@Autowired
	private UserFinancialServiceImpl userFinancialServiceImpl;
	@Autowired
	private UserFinancialDetailServiceImpl userFinancialDetailServiceImpl;
	@Autowired
	private UserInviteServiceImpl userInviteServiceImpl;
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	public void Pay(String orderNumber) {
		try {
			Order orderData = orderServiceImpl.selectOrderByNumber(orderNumber);
			if (orderData != null && orderData.getOrderState() == 0) {

				Order entityOrder = new Order();
				entityOrder.setOrderNumber(orderNumber);
				entityOrder.setOrderState(10);//10已付款
				// 更新订单状态
				orderServiceImpl.updateOrder(entityOrder);
				// 获取产品详情
				Product product = productServiceImpl.selectProductById(orderData.getProductId());
				// 更新用户级别
				User entityUser = new User();
				entityUser.setUserId(orderData.getUserId());
				entityUser.setUserLevel(product.getProductType());
				userServiceImpl.updateUser(entityUser);
				// 增加用户充值记录
				UserRecharge rechargeData = userRechargeServiceImpl.selectRechargeByuserId(orderData.getUserId());
				UserRecharge entityRecharge = new UserRecharge();
				entityRecharge.setUserId(orderData.getUserId());
				if (rechargeData != null && rechargeData.getRechargeId() > 0) {
					entityRecharge.setRechargeId(rechargeData.getRechargeId());
					if (rechargeData.getIsValid() == 1) // 如果有效 结束时间往上累加
					{
						Date d = DateUtil.addDay(rechargeData.getEndTime(), product.getDay());
						entityRecharge.setTotalMoney(rechargeData.getTotalMoney() + product.getPrice());
						entityRecharge.setEndTime(d);
						entityRecharge.setValidDays(rechargeData.getValidDays() + product.getDay());

					} else {
						/**
						 * 如果无效了从新开始-- 要更新 开始时期为当天
						 */
						entityRecharge.setIsValid(RechargeValid.getRechargeByCode(1).getValidCode());
						entityRecharge.setBeginTime(new Date());
						entityRecharge.setTotalMoney(product.getPrice());
						entityRecharge.setEndTime(DateUtil.addDay(new Date(), product.getDay()));
						entityRecharge.setValidDays(product.getDay());
					}
					userRechargeServiceImpl.updateRecharge(entityRecharge);

				} else {

					// 直接插入
					entityRecharge.setIsValid(RechargeValid.getRechargeByCode(1).getValidCode());
					entityRecharge.setTotalMoney(product.getPrice());
					entityRecharge.setEndTime(DateUtil.addDay(new Date(), product.getDay()));
					entityRecharge.setValidDays(product.getDay());
					userRechargeServiceImpl.insertRecharge(entityRecharge);
				}
				// 更新用户账户给邀请人返还20%钱 用于提现
				double reward = product.getPrice() * SystemConfig.percentage;

				// 找到邀请人
				UserInvite invite = userInviteServiceImpl.selectInviteByregisterId(orderData.getUserId());
				if (invite != null) {
					UserFinancial financial = userFinancialServiceImpl.selectFinancial(invite.getInviteUserId());
					UserFinancial userFinacial = new UserFinancial();
					userFinacial.setUserId(invite.getInviteUserId());
					if (financial != null && financial.getFinancialId() > 0) {
						// 更新
						userFinacial.setTotalMoney(financial.getTotalMoney() + reward);
						userFinacial.setTotalRevenue(financial.getTotalRevenue() + reward);
						userFinancialServiceImpl.updateFinancial(userFinacial);

					} else {
						// 插入一条
						userFinacial.setTotalMoney(reward);
						userFinacial.setTotalRevenue(reward);
						userFinacial.setPayAccount("");
						userFinacial.setPayRealName("");
						userFinacial.setPhone("");
						userFinancialServiceImpl.insertFinancial(userFinacial);
					}
					// 插入明细
					UserFinancialDetail detail = new UserFinancialDetail();
					detail.setUserId(invite.getInviteUserId());
					detail.setFinancialType(FinancialType.getFinancialTypeByCode(1).getTypeId());
					detail.setFinancialMoney(reward);
					detail.setOperateDate(new Date());
					detail.setOperateStatus(FinancialOperateStatus.getFinancialOperateStatusByCode(10).getStateCode());
					detail.setSourceNumber(orderNumber);
					userFinancialDetailServiceImpl.insertFinancialDetail(detail);
				}

				// orderData.getProductId()
			}

		} catch (Exception e) {
			logger.error("支付宝回调错误订单号：" + orderNumber + "异常信息" + e.getMessage() + "堆栈信息" + e.getStackTrace());
		}

	}
}
