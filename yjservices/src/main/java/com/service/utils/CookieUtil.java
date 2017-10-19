package com.service.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	/**
	 * @Description: 娣诲姞cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 * @return
	 * 
	 * @Title: CookieUtil.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-1-15 涓婂崍10:29:29
	 * @version V2.0
	 */
	public static Cookie addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
		return cookie;
	}

	public static void RemoveCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(name)) {
					Cookie cookie = new Cookie(name, null);// 杩欒竟寰楃敤"",涓嶈兘鐢╪ull
					cookie.setValue(null);
					cookie.setPath("/");// 璁剧疆鎴愯窡鍐欏叆cookies涓�鏍风殑
					// cookie.setDomain(".wangwz.com");//璁剧疆鎴愯窡鍐欏叆cookies涓�鏍风殑
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		Cookie cookies1[] =request.getCookies();
	}

	/**
	 * @Description: 鏍规嵁key鑾峰彇Cookie瀵硅薄
	 * 
	 * @param request
	 * @param key
	 * @return
	 * 
	 * @Title: CookieUtil.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-1-15 涓婂崍10:29:44
	 * @version V2.0
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String key) {
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (cookieMap.containsKey(key)) {
			Cookie cookie = (Cookie) cookieMap.get(key);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * @Description: 灏佽Cookie璇诲彇鏂规硶
	 * 
	 * @param request
	 * @return
	 * 
	 * @Title: CookieUtil.java
	 * @Copyright: Copyright (c) 2014
	 * 
	 * @author Comsys-LZP
	 * @date 2014-1-15 涓婂崍10:30:05
	 * @version V2.0
	 */
	private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
}
