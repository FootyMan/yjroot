package com.service.utils;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	public static Integer getSafeInt(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		String key = request.getParameter("key");
		if ((tempStr == null) || ("".equals(tempStr)) || ("null".equalsIgnoreCase(tempStr))) {
			return Integer.valueOf(0);
		}
		if (Encriptor.getMD5("saftkey" + tempStr).equalsIgnoreCase(key)) {
			return Integer.valueOf(Integer.parseInt(tempStr));
		}
		return Integer.valueOf(0);
	}

	public static Integer getInt(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		if ((tempStr == null) || ("".equals(tempStr)) || ("null".equalsIgnoreCase(tempStr))) {
			return Integer.valueOf(0);
		}
		return Integer.valueOf(Integer.parseInt(tempStr));
	}

	public static Long getLong(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		if ((tempStr == null) || ("".equals(tempStr)) || ("null".equalsIgnoreCase(tempStr))) {
			return null;
		}
		return Long.valueOf(tempStr);
	}

	public static Long getSafeLong(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		String key = request.getParameter("key");
		if ((tempStr == null) || ("".equals(tempStr)) || ("null".equalsIgnoreCase(tempStr))) {
			return null;
		}
		if (Encriptor.getMD5("saftkey" + tempStr).equalsIgnoreCase(key)) {
			return Long.valueOf(Long.parseLong(tempStr));
		}
		return null;
	}

	public static String getUBBStr(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		if ((tempStr == null) || ("".equals(tempStr)) || ("null".equalsIgnoreCase(tempStr))) {
			return "";
		}
		return tempStr.replaceAll("&", "&amp;").replace("\"", "&quot;").replace("\t", "&nbsp;&nbsp;")
				.replace(" ", "&nbsp;").replace("<", "&lt;").replaceAll(">", "&gt;");
	}

	public static String getClearStr(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		if ((tempStr == null) || ("".equals(tempStr)) || ("null".equalsIgnoreCase(tempStr))) {
			return "";
		}
		return tempStr.replaceAll("\r\n", "").replaceAll("</?(a|A)(.*?)>", "")
				.replaceAll("<p[^>]*?>((&nbsp;)|(\r?\n)|(\\s))*?</p>", "").replaceAll("</?(b|B)(r|R)(.*?)>", "")
				.replaceAll("\r\n{2,}", "\r\n\r\n").replaceAll("&nbsp;{3,}", "&nbsp;&nbsp;");
	}

	public static String getSafeStr(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		String key = request.getParameter("key");
		if ((tempStr == null) || ("".equals(tempStr)) || ("null".equalsIgnoreCase(tempStr))) {
			return "";
		}
		if (Encriptor.getMD5("saftkey" + tempStr).equalsIgnoreCase(key)) {
			return tempStr;
		}
		return "";
	}

	public static int getInt(HttpServletRequest request, String str, int defaultInt) {
		String tempStr = request.getParameter(str);
		int tempInt = 0;
		if ((tempStr == null) || ("".equals(tempStr)) || ("null".equalsIgnoreCase(tempStr)))
			return defaultInt;
		try {
			tempInt = Integer.parseInt(tempStr);
		} catch (Exception e) {
			tempInt = defaultInt;
		}
		return tempInt;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static Date getUtilDate(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);

		Date tempDate = null;
		if (tempStr != null) {
			try {
				tempDate = GlobalsConstant.DF_YYMMDD_HHMMSS.parse(tempStr);
			} catch (Exception e) {
				tempDate = null;
			}
		}

		return tempDate;
	}
}
