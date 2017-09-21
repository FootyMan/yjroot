package com.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.api.business.UserBusiness;
import com.api.model.ProfitAccountRequest;
import com.api.model.ProfitMyResponse;
import com.api.model.ProfitRecordRequest;
import com.api.model.ProfitRecordResponse;
import com.api.model.ProfitWithDrawResponse;
import com.api.model.baseRequest;
import com.api.model.baseResponse;
import com.api.model.userModel;
import com.api.requestresponse.ResponseEncryptBody;
import com.api.utils.ResponseUtils;
import com.api.utils.ResultEnum;
import com.myErp.enums.FinancialOperateStatus;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/profit", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
		MediaType.APPLICATION_JSON_VALUE })
@Api(tags = "用户收益接口")
public class ProfitController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UserVerifyCodeServiceImpl userVerifyCodeServiceImpl;
	@Autowired
	private UserFinancialServiceImpl userFinancialServiceImpl;
	@Autowired
	private UserFinancialDetailServiceImpl userFinancialDetailServiceImpl;

	/**
	 * 绑定支付宝账号
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/account", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-registe", value = "绑定支付宝账号", notes = "绑定支付宝账号")
	public baseResponse<?> BindAccount(@ApiParam(value = "输入") @RequestBody baseRequest<ProfitAccountRequest> request) {
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
	 * 我的收益
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/myProfit", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-registe", value = "我的收益", notes = "我的收益")
	public baseResponse<ProfitMyResponse> MyProfit(@ApiParam(value = "输入") @RequestBody baseRequest<?> request) {
		baseResponse<ProfitMyResponse> response = new baseResponse<ProfitMyResponse>();
		// 验证码是否正确
		UserFinancial financial = userFinancialServiceImpl.selectFinancial(request.getUserId());
		if (financial != null && financial.getFinancialId() > 0) {
			ProfitMyResponse myProfit = new ProfitMyResponse();
			myProfit.setCanMoney(financial.getTotalMoney());
			myProfit.setTotalMoney(financial.getTotalRevenue());
			response.setData(myProfit);
		}
		return response;
	}

	/**
	 * 收益和提现记录
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	@ResponseEncryptBody
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	@ApiOperation(nickname = "swagger-registe", value = "收益和提现记录 注意返回的body里是集合 字段类型全部String"
			+ "收益记录返回date=日期	user=用户	type=会员类型	money=佣金"
			+ "提现记录date=日期	money=提现金额		state=结算状态", notes = "收益和提现记录")
	public baseResponse ProfitRecord(@ApiParam(value = "输入") @RequestBody baseRequest<ProfitRecordRequest> request) {
		baseResponse response = new baseResponse();
		ProfitRecordRequest recordRequest = request.getbody();
		if (recordRequest != null && recordRequest.getType() > 0) {

			//收益记录
			if (recordRequest.getType() == 1) {

				List<ProfitRecordResponse> recordResponses = new ArrayList<ProfitRecordResponse>();
				List<MyProfitRecord> profitRecords = userFinancialDetailServiceImpl.selectMyProfit(request.getUserId());
				for (MyProfitRecord myProfitRecord : profitRecords) {
					ProfitRecordResponse item = new ProfitRecordResponse();
					item.setDate(DateUtil.DateToString(myProfitRecord.getOperateDate(), DateStyle.YYYY_MM_DD_HH_MM));
					item.setUser(myProfitRecord.getNickName());
					item.setType(myProfitRecord.getProductDesc());
					item.setMoney("¥"+myProfitRecord.getFinancialMoney());
					recordResponses.add(item);
				}
				response.setData(recordResponses);
			}
			//支出记录
			else
			{
				List<ProfitWithDrawResponse> WithDrawResponses = new ArrayList<ProfitWithDrawResponse>();
				List<UserFinancialDetail> financialDetails=userFinancialDetailServiceImpl.selectFinancialDetail(request.getUserId());
				for (UserFinancialDetail userFinancialDetail : financialDetails) {
					ProfitWithDrawResponse item=new ProfitWithDrawResponse();
					item.setDate(DateUtil.DateToString(userFinancialDetail.getOperateDate(), DateStyle.YYYY_MM_DD_HH_MM));
					item.setMoeny("¥"+userFinancialDetail.getFinancialMoney());
					item.setState(FinancialOperateStatus.getFinancialOperateStatusByCode(userFinancialDetail.getOperateStatus()).getDesc());
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
}
