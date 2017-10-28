package com.api.utils;

public class ResultEnum {

	/**
	 * 成功码
	 */
	public static int SuccessCode = 200;
	/**
	 * 服务器错误
	 */
	public static int ServiceErrorCode = 500;
	
	/**
	 * 服务器验证
	 * 先决条件失败 
	 */
	public static int VerificationCode=412;
	/**
	 * 验签错误
	 */
	public static int SignErrorCode = 100;
	/**
	 * 字段未空
	 */
	public static int ColmunErrorCode = 250;

	/**
	 * 默认纬度
	 */
	public static double defaultLat = 39.9151190;
	/**
	 * 默认经度
	 */
	public static double defaultLon = 116.4039630;
	/**
	 * 每日提现上限
	 */
	public static double Quota = 500;
	/**
	 * 默认签名
	 */
	public static String defaultSign="这家伙很懒，什么也没留下....";
}
