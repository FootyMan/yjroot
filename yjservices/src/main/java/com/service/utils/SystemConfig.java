package com.service.utils;

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
	public static double percentage = Double.parseDouble(PropertiesUtils.ReadProperties(fileName, "reward.percentage"));

	/**
	 * 
	 * 环境标识-测试环境 dev,qa,production线上环境用
	 */
	public static String EnvIdentity = PropertiesUtils.ReadProperties(fileName, "EnvIdentity");

	/**
	 * 支付宝回调地址
	 */
	public static String AlipayNotifyurl = PropertiesUtils.ReadProperties(fileName, "alipay_notify_url");

	/**
	 * 是否从缓存读取数据
	 */
	public static boolean IsReadRedis = Boolean.parseBoolean(PropertiesUtils.ReadProperties(fileName, "isread_redis"));

	/**
	 * 图片前缀
	 */
	public static String ImgurlPrefix = PropertiesUtils.ReadProperties(fileName, "img_url");

	/**
	 * 二维码路径
	 */
	public static String TwoCodeUrl = PropertiesUtils.ReadProperties(fileName, "twoCode_url");
	/**
	 * 最小提现金额
	 */
	public static double Min_Withdrawals = Double.parseDouble(PropertiesUtils.ReadProperties(fileName, "min_withdrawals"));

}
