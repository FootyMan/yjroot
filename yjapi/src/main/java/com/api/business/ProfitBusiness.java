package com.api.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.pool.BaseKeyedObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.request.ProfitAccountRequest;
import com.api.request.ProfitRecordRequest;
import com.api.request.ProfitWithDrawRequest;
import com.api.request.baseRequest;
import com.api.response.ProfitMyResponse;
import com.api.response.ProfitRecordResponse;
import com.api.response.ProfitWithDrawResponse;
import com.api.response.baseResponse;
import com.api.utils.ResultEnum;
import com.myErp.enums.FinancialOperateStatus;
import com.myErp.enums.FinancialType;
import com.myErp.impl.UserFinancialDetailServiceImpl;
import com.myErp.impl.UserFinancialServiceImpl;
import com.myErp.impl.UserServiceImpl;
import com.myErp.impl.UserVerifyCodeServiceImpl;
import com.myErp.manager.bean.MyProfitRecord;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserFinancial;
import com.myErp.manager.bean.UserFinancialDetail;
import com.myErp.manager.bean.UserVerifyCode;
import com.myErp.utils.DateStyle;
import com.myErp.utils.DateUtil;
import com.myErp.utils.StringUtils;

@Service("ProfitBusiness")
public class ProfitBusiness {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserVerifyCodeServiceImpl userVerifyCodeServiceImpl;
	@Autowired
	private UserFinancialServiceImpl userFinancialServiceImpl;
	@Autowired
	private UserFinancialDetailServiceImpl userFinancialDetailServiceImpl;

	/**
	 * 绑定支付账户
	 * 
	 * @param request
	 * @return
	 */
	public baseResponse<?> BindAccount(baseRequest<ProfitAccountRequest> request) {

		baseResponse<?> response = new baseResponse<Object>();

		ProfitAccountRequest accountRequestModel = request.getbody();
		// 验证码是否正确
		User userData = userServiceImpl.selectUserByUserId(request.getUserId());
		if (userData != null && !StringUtils.isEmpty(userData.getPhone())) {
			UserVerifyCode userCode = userVerifyCodeServiceImpl.selectCodeByphone(userData.getPhone());
			if (userCode == null || !userCode.getVerifyCode().equals(accountRequestModel.getVerifyCode())) {
				response.setCode(ResultEnum.ServiceErrorCode);
				response.setMsg("手机验证码不正确");
				return response;
			}
			UserFinancial financial = userFinancialServiceImpl.selectFinancial(request.getUserId());
			UserFinancial entityFinancial = new UserFinancial();
			entityFinancial.setUserId(request.getUserId());
			entityFinancial.setPayAccount(accountRequestModel.getAccount());
			entityFinancial.setPayRealName(accountRequestModel.getName());
			entityFinancial.setPhone(userData.getPhone());
			if (financial == null || financial.getFinancialId() <= 0) {
				// 添加
				userFinancialServiceImpl.insertFinancial(entityFinancial);
			} else {
				userFinancialServiceImpl.updateFinancial(entityFinancial);
			}

		} else {
			response.setCode(ResultEnum.ColmunErrorCode);
			response.setMsg("用户不存在");
		}
		return response;
	}

	/**
	 * 获取我的收益
	 * 
	 * @param request
	 * @return
	 */
	public baseResponse<ProfitMyResponse> GetMyProfit(baseRequest<?> request) {
		baseResponse<ProfitMyResponse> response = new baseResponse<ProfitMyResponse>();
		UserFinancial financial = userFinancialServiceImpl.selectFinancial(request.getUserId());
		if (financial != null && financial.getFinancialId() > 0) {
			ProfitMyResponse myProfit = new ProfitMyResponse();
			myProfit.setCanMoney(financial.getTotalMoney());
			myProfit.setTotalMoney(financial.getTotalRevenue());
			myProfit.setQuota(ResultEnum.Quota);
			myProfit.setAccount(financial.getPayAccount());
			response.setData(myProfit);
		}
		return response;
	}

	/**
	 * 收益和提现记录
	 * 
	 * @param request
	 * @return
	 */
	public baseResponse<?> ProfitRecord(baseRequest<ProfitRecordRequest> request) {
		baseResponse response = new baseResponse();
		ProfitRecordRequest recordRequest = request.getbody();
		if (recordRequest != null && recordRequest.getType() > 0) {

			// 收益记录
			if (recordRequest.getType() == 1) {

				List<ProfitRecordResponse> recordResponses = new ArrayList<ProfitRecordResponse>();
				List<MyProfitRecord> profitRecords = userFinancialDetailServiceImpl.selectMyProfit(request.getUserId());
				for (MyProfitRecord myProfitRecord : profitRecords) {
					ProfitRecordResponse item = new ProfitRecordResponse();
					item.setDate(DateUtil.DateToString(myProfitRecord.getOperateDate(), DateStyle.YYYY_MM_DD_HH_MM));
					item.setUser(myProfitRecord.getNickName());
					item.setType(myProfitRecord.getProductDesc());
					item.setVip(myProfitRecord.getUserLevel());
					item.setMoney("¥" + myProfitRecord.getFinancialMoney());
					recordResponses.add(item);
				}
				response.setData(recordResponses);
			}
			// 支出记录
			else {
				List<ProfitWithDrawResponse> WithDrawResponses = new ArrayList<ProfitWithDrawResponse>();
				UserFinancialDetail entitydetail = new UserFinancialDetail();
				entitydetail.setUserId(request.getUserId());
				entitydetail.setFinancialType(2);
				List<UserFinancialDetail> financialDetails = userFinancialDetailServiceImpl
						.selectFinancialDetail(entitydetail);
				for (UserFinancialDetail userFinancialDetail : financialDetails) {
					ProfitWithDrawResponse item = new ProfitWithDrawResponse();
					item.setDate(
							DateUtil.DateToString(userFinancialDetail.getOperateDate(), DateStyle.YYYY_MM_DD_HH_MM));
					item.setMoeny("¥" + userFinancialDetail.getFinancialMoney());
					item.setState(FinancialOperateStatus
							.getFinancialOperateStatusByCode(userFinancialDetail.getOperateStatus()).getDesc());
					item.setStateId(userFinancialDetail.getOperateStatus());
					WithDrawResponses.add(item);
				}
				response.setData(WithDrawResponses);
			}
		} else {
			response.setCode(ResultEnum.ColmunErrorCode);
			response.setMsg("请求类型为空");
		}
		return response;

	}

	/**
	 * 提现
	 * 
	 * @param request
	 * @return
	 */
	public baseResponse<?> UserWithdrawals(baseRequest<ProfitWithDrawRequest> request) {
		baseResponse<?> response = new baseResponse<Object>();
		ProfitWithDrawRequest body = request.getbody();
		// 验证码是否正确
		User userData = userServiceImpl.selectUserByUserId(request.getUserId());
		if (userData != null && !StringUtils.isEmpty(userData.getPhone())) {
			UserVerifyCode userCode = userVerifyCodeServiceImpl.selectCodeByphone(userData.getPhone());
			if (userCode == null || !userCode.getVerifyCode().equals(body.getVerifyCode())) {
				response.setCode(ResultEnum.ServiceErrorCode);
				response.setMsg("手机验证码不正确");
				return response;
			}
		}
		// 获取收入主表信息 判断可提现金额和请求过来的金额是否匹配 可提现金额不能小于提现金额
		UserFinancial financial = userFinancialServiceImpl.selectFinancial(request.getUserId());
		if (financial != null && financial.getTotalMoney() <= body.getMoney()) {
			// 更新主表提现金额 递减去
			// 可提现金额=可提现金额-提现金额
			// 累计支出金额=累计额支出金额+提现金额
			UserFinancial entityFinancial = new UserFinancial();
			entityFinancial.setUserId(request.getUserId());
			entityFinancial.setTotalMoney(financial.getTotalMoney() - body.getMoney());
			entityFinancial.setTotalWithDraw(financial.getTotalWithDraw() + body.getMoney());
			userFinancialServiceImpl.updateFinancial(entityFinancial);
			// 添加明细表状态1提现中
			UserFinancialDetail entityDetail = new UserFinancialDetail();
			entityDetail.setUserId(request.getUserId());
			entityDetail.setFinancialType(FinancialType.getFinancialTypeByCode(2).getTypeId());// 提现
			entityDetail.setFinancialMoney(-body.getMoney());
			entityDetail.setOperateDate(new Date());
			entityDetail.setOperateStatus(FinancialOperateStatus.getFinancialOperateStatusByCode(1).getStateCode());
			entityDetail.setSourceNumber(StringUtils.GetOrderNumberWithdrawals(request.getUserId()));
			userFinancialDetailServiceImpl.insertFinancialDetail(entityDetail);

		} else {
			response.setCode(ResultEnum.ServiceErrorCode);
			response.setMsg("提现金额不符");
		}
		response.setMsg("提现申请已提交，请3-5工作日注意支付宝账号");
		return response;
	}

}
