package com.api.utils;

public class ResponseUtils {

	/**
	 * 获取距离
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
}
