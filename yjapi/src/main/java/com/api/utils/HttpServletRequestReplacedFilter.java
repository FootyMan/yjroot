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

import com.api.model.baseRequest;
import com.myErp.utils.CommonMethod;
import com.myErp.utils.SystemConfig;

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
					response.getWriter().write("{\"code\": 2009,\"msg\": \"非法请求\"}");

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
