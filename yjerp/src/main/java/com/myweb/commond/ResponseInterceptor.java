package com.myweb.commond;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.service.utils.CookieUtil;

public class ResponseInterceptor implements HandlerInterceptor {
	// @Autowired
		// private ResponseHolder responseHolder;

		@Override
		public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
				throws Exception {
			// TODO Auto-generated method stub
			//System.out.println("afterCompletion");

		}

		/**
		 * 
		 * 执行完action之后执行postHandle 可用于所有参数追加元素
		 */
		@Override
		public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
				throws Exception {
			// TODO Auto-generated method stub

//			if (arg3 != null) {
//				Map<String, Object> modelMap = arg3.getModel();
//			}

	 
		}

		/**
		 * 
		 * 在请求action处理之前执行 true继续往下走 false直接return
		 */
		@Override
		public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
			// TODO Auto-generated method stub
			//System.out.println(arg0.getRequestURL());
		 
			System.out.println(arg0.getRequestURI());
			String actioin=(arg0.getContextPath());
			//System.out.println(actioin);
//			if (actioin.equals("/test1")) {
//				return true;
//			} else {
//					return false;
//			}
//			System.out.println(arg0.getServletPath());
//			System.out.println(arg0.getQueryString());
			
			Cookie cookie = CookieUtil.getCookieByName(arg0, "Ticket");
//			String curPath=request.getRequestURL().toString();
//	        System.out.println("===>> curpath:"+curPath);
//	        if (curPath.indexOf("GPS/User/Index")>=0){
//	            return true;
//	        }
//	        if(null==user || "".equals(user)){
//	            return true;
			return true;
		}

}

