package com.api.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.api.request.baseRequest;
import com.api.response.baseResponse;
import com.myErp.utils.CommonMethod;
import com.myErp.utils.SystemConfig;

import javassist.runtime.Desc;

public class HttpServletRequestReplacedFilter implements Filter {
	private static Logger logger = Logger.getLogger(HttpServletRequestReplacedFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		response.setCharacterEncoding("UTF-8");
		HttpServletRequest requestWrapper = null;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			if ("POST".equals(httpServletRequest.getMethod().toUpperCase())
					&& httpServletRequest.getContentType().equalsIgnoreCase("application/json")
					&& SystemConfig.isSign) {
				requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
				// String url = requestWrapper.getRequestURL().toString();
				String reqStr = CommonMethod.ReadInputStreamFromClientRequest(requestWrapper);
				logger.info(reqStr);
				baseRequest model = CommonMethod.ConvertJsonToObj(reqStr, baseRequest.class);
				if (model != null && model.getTimeStamp().equals("11232")) {
					chain.doFilter(requestWrapper, response);
				} else {
					baseResponse<?> r = new baseResponse<Object>();
					r.setCode(ResultEnum.SignErrorCode);
					r.setMsg("非法请求");
					String json = CommonMethod.ConvertObjToJson(r);
					try {
						String des = DES.encrypt(json);
						response.getWriter().write(des);// "{\"code\":
														// 2009,\"msg\":
														// \"非法请求\"}"
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}

		if (requestWrapper == null) {
			chain.doFilter(request, response);
		}
		// else {
		// chain.doFilter(requestWrapper, response);
		// }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
