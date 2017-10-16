package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.api.utils.ExceptionHandler;
import com.myErp.enums.UserLevel;
import com.myErp.impl.UserRechargeServiceImpl;
import com.myErp.impl.UserServiceImpl;
import com.myErp.manager.bean.User;
import com.myErp.manager.bean.UserRecharge;

import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@Controller
@RequestMapping(value = "/job")
// @Api(tags = "服务端自用接口 执行作业")
public class JobController {

	@Autowired
	private UserRechargeServiceImpl userRechargeServiceImpl;
	@Autowired
	private UserServiceImpl userServiceImpl;
	private static Logger logger = Logger.getLogger(ExceptionHandler.class);

	/**
	 * 更新即将到期会员
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/expire", method = RequestMethod.GET)
	@ApiOperation(nickname = "swagger-initUser", value = "服务端自用接口 执行作业", notes = "更新即将到期的会员 降级为普通会员")
	public void UpExpireUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 执行购买会员人员时间到期更新会员级别
		// 获取当前时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		List<UserRecharge> listRecharge = userRechargeServiceImpl.GetExpireUser(format.format(new Date()));
		for (UserRecharge x : listRecharge) {

			// 更新会员级别
			User user = new User();
			user.setUserId(x.getUserId());
			user.setUserLevel(UserLevel.getOrderStateByCode(1).getUserLevel());
			int result = userServiceImpl.updateUser(user);
			if (result > 0) {
				// 更新会员有效期——》为无效
				UserRecharge userRecharge = new UserRecharge();
				userRecharge.setUserId(x.getUserId());
				userRecharge.setIsValid(2);// 无效
				userRechargeServiceImpl.updateRecharge(userRecharge);
			}
			logger.info("会员:" + x.getUserId() + "该会员已到期 降级为普通会员");
		}

		response.setHeader("Content-type", "text/html;charset=UTF-8");
		// 这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
		response.setCharacterEncoding("UTF-8");
		logger.info("更新即将到期会员定时任务被执行：执行时间:" + format.format(new Date()));
		response.getWriter().write("定时任务被执行：执行时间:" + format.format(new Date()));
	}
}
