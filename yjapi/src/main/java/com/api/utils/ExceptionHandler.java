package com.api.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.api.response.BaseResponse;
import com.service.utils.CommonMethod;

public class ExceptionHandler implements HandlerExceptionResolver {

	private static Logger logger = Logger.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) {
		// TODO Auto-generated method stub
		arg1.setCharacterEncoding("utf-8");
		logger.error("发生位置:" + arg2 + "堆栈：" + arg3.getStackTrace() + "异常信息:" + arg3.getMessage());
		try {
			BaseResponse response = new BaseResponse();
			response.setCode(500);
			response.setMsg("服务器异常！");
			String reqStr = CommonMethod.ConvertObjToJson(response);
			String des =DecryptEncryptUtils.doEncryptResponseBody(reqStr);
			arg1.getWriter().write(des);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView();
	}

}
