package com.api.utils;

import java.util.Calendar;
import java.util.Date;

import com.myErp.utils.DateUtil;

public class ResponseUtils {

	/**
	 * 获取距离
	 * 
	 * @param Range
	 * @return
	 */
	public static String GetRange(double Range) {
		if (Range < 1000) {
			return ((int) Range + "m");
		} else {
			double b = Math.rint(Range / 100) / 10;// 这个结果是你要的千米
			return (b + "km");
		}
	}

	/**
	 * 获取访问时间
	 * 
	 * @param date
	 * @return
	 */
	public static String GetBrowseTime(Date date) {
		String browesTime = "";
		Calendar c1 = Calendar.getInstance();// 当前时间
		Calendar c2 = Calendar.getInstance();// 访问时间
		c2.setTime(date);
		if (c1.get(Calendar.DATE) == c2.get(Calendar.DATE)) {
			browesTime = DateUtil.getHH_MM(c2.getTime()) + "来访";

		} else {
			browesTime = DateUtil.getDate(c2.getTime()) + "来访";

		}
		return browesTime;
	}
}
