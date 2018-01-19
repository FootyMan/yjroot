package com.service.alipay.sdk;

import com.service.utils.SystemConfig;

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2017092608934726";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKj2e8QDerrqTuDmmRrcA9ASsbAZHsr/OJUI6v81CtRw24M5QGgaVMxkfXujiS0+7e1yDlJFZ4tWhqySGUwMT4FiZNKP+7B53v49rrUY+/RyS3vuQAZVrpQ95uDbutgZ5G0Ew79PbAhFaw9wFb3Jvmtc4TrkArfVM5I4qavFeKAtAgMBAAECgYBpZNbu/fyge2eYgj8esf7u27pazlCW73yr+lQbvUp0GbHTuPKj/UjH9icP6IK45ngjlPG5//EGyYJYQwnklglA91kZ8cM4UPQudDMq1/g0R89x93BphOuHnbqQuVlv/9Fp4nLQRpvrOnCZG6krqd/uhPMxw9EF+YoKcJSJnnr+AQJBAOMJYhbn3LWf8vrySAKCIadbcHGkDAglTp1iYsmfeZtcip8M1DCFGu1u3tgKTkkCFBgsa6jwMW3XdEATy1lPhOUCQQC+hILw7ePfOIj0LCZ68/xGDgrXrBpuq1w2mNeFXldsL6bBtwBnRGXwglRjIIfuekJlqPHBHeLRd4u4ENpkUQGpAkBN0GN8Mk/lOEMRW4Tuxyc9swthB7bP++lr69KsBCqr4xXn2X/IeXZ3Nm7OYQnf4AR/HIpxxpFKcnP3mlEx9R05AkBaRlP9cdu+E/3hJWJ+dEofr9u3YleMBSrGvBmzmoZ2PwYXWG2agTCEjF0MM4f1mn0HysvzJAQsGSddaq7xRekxAkEAk4KdUwmPrAjocbDweU3Cn7/YiPnvVcddk2QqncWt7Y5ZUZjXtrp/bzsMk5PupAuWf65Vuj4SrRtPQPfZF8Y0xA==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
	// 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCo9nvEA3q66k7g5pka3APQErGwGR7K/ziVCOr/NQrUcNuDOUBoGlTMZH17o4ktPu3tcg5SRWeLVoaskhlMDE+BYmTSj/uwed7+Pa61GPv0ckt77kAGVa6UPebg27rYGeRtBMO/T2wIRWsPcBW9yb5rXOE65AK31TOSOKmrxXigLQIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = SystemConfig.ServerApiAddress + "payNotif/aliNotif";

	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// 商户可以自定义同步跳转地址
	public static String return_url = SystemConfig.ServerErpAddress+"wappay/return_url.jsp";
	// 签名方式
	public static String sign_type = "RSA";

	// 字符编码格式
	public static String charset = "utf-8";

	// 字符编码格式
	public static String sellerId = "sunwei@91yj.cc";
	// 请求网关地址
	public static String URL = "https://openapi.alipay.com/gateway.do";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝网关
	// public static String gatewayUrl =
	// "https://openapi.alipay.com/gateway.do";

	// 支付宝网关
	// public static String log_path = "C:\\";
}
