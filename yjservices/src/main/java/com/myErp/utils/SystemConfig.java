package com.myErp.utils;

/**
 * 
 * @author HCY
 * 
 *
 */
public class SystemConfig {

	/**
	 * 文件名称
	 */
	public static String fileName = "System.properties";
	public static String imgftphost = PropertiesUtils.ReadProperties(fileName, "img.ftp.host");
	/**
	 * 用户上传图片存储地址
	 */
	public static String imguserpath = PropertiesUtils.ReadProperties(fileName, "img.user.path");
	/**
	 * 是否验签
	 */
	public static boolean isSign = Boolean.parseBoolean(PropertiesUtils.ReadProperties(fileName, "isSign"));
	/**
	 * 二次启动页
	 */
	public static String pageTwo = PropertiesUtils.ReadProperties(fileName, "pageTwo.imgUrl");
	/**
	 * 是否加密
	 */
	public static boolean isEncrypt = Boolean.parseBoolean(PropertiesUtils.ReadProperties(fileName, "isEncrypt"));
	/**
	 * 奖励百分比
	 */
	public static double percentage=Double.parseDouble(PropertiesUtils.ReadProperties(fileName, "reward.percentage"));
}
