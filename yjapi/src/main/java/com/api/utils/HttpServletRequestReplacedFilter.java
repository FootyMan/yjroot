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

import com.alibaba.fastjson.JSON;
import com.api.request.baseRequest;
import com.api.response.BaseResponse;
import com.google.gson.Gson;
import com.service.utils.CommonMethod;
import com.service.utils.Md5Util;
import com.service.utils.SystemConfig;

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
				Gson gson = new Gson();
				baseRequest<Object> model = gson.fromJson(reqStr, baseRequest.class);
				if (model != null && model.getSign().toUpperCase().equals(GetSign(model.getTimeStamp()))) {
					chain.doFilter(requestWrapper, response);
				} else {
					BaseResponse<?> r = new BaseResponse<Object>();
					r.setCode(ResultEnum.SignErrorCode);
					r.setMsg("非法请求");
					String json = CommonMethod.ConvertObjToJson(r);
					json = DecryptEncryptUtils.doEncryptResponseBody(json);
					response.getWriter().write(json);// "{\"code\":
														// 2009,\"msg\":
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

	/**
	 * 获取签名
	 * 
	 * @param timeStamp
	 * @return
	 */
	public String GetSign(String timeStamp) {
		String sign = CommonConfig.signKey + timeStamp;
		return Md5Util.stringByMD5(sign);
	}

}
