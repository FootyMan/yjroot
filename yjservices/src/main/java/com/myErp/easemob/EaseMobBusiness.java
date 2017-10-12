package com.myErp.easemob;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.RequestToViewNameTranslator;

import com.google.gson.Gson;
import com.myErp.utils.HttpClientUtil;
import com.myErp.utils.StringUtils;

//import com.google.code.gson;
/**
 * 环信注册
 * 
 * @author HCY
 *
 */
public class EaseMobBusiness {

	/**
	 * 换成token
	 */
	private static String token = "";

	static String reqUrlFormat = "https://a1.easemob.com/";
	/// <summary>
	/// 客户号
	/// </summary>
	public static String clientID = "YXA6hY-h8JIZEeexijuygdF8sg";
	/// <summary>
	/// 客户端
	/// </summary>
	public static String clientSecret = "YXA6b0ouUgc1Hxi_-6zqvMot30CdVYE";
	/// <summary>
	/// app名称
	/// </summary>
	public static String appName = "yujian";
	/// <summary>
	/// #号后面的名称
	/// </summary>
	public static String orgName = "1175170905115725";
	/// <summary>
	/// 设备号
	/// </summary>
	// private static String token =
	/// "YWMt4vSp3laZEeWsXb11In9CjAAAAVDk6KipG3nDMvPks9BA49Y-GHAJgbFNgfU";

	private static String easeMobUrl;

	public static String getEaseMobUrl() {
		return String.format(reqUrlFormat + "%s/%s/", orgName, appName);
	}

	/// <summary>
	/// 使用app的client_id 和 client_secret登陆并获取授权token
	/// </summary>
	/// <returns></returns>
	public static String QueryToken() {
		if (StringUtils.isEmpty(clientID) || StringUtils.isEmpty(clientSecret)) {
			return "";
		}

		if (!StringUtils.isEmpty(token)) {
			return token;
		}
		String postUrl = getEaseMobUrl() + "token";
		String parameter = "{\"grant_type\":\"client_credentials\",\"client_id\":\"%s\",\"client_secret\":\"%s\"}";
		parameter = String.format(parameter, clientID, clientSecret);
		String postResultStr = HttpClientUtil.post(postUrl, parameter);
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map = gson.fromJson(postResultStr, map.getClass());
		token = (String) map.get("access_token");
		return token;
	}

	/// <summary>
	/// 创建用户
	/// </summary>
	/// <param name="userName">账号</param>
	/// <param name="password">密码</param>
	/// <returns>创建成功的用户JSON</returns>
	public static String AccountCreate(String userName) {
		String parameter = "{\"username\":\"%s\",\"password\":\"%s\"}";
		parameter = String.format(parameter, userName, "yj123");
		// return ReqUrl(getEaseMobUrl() + "users", parameter, token);
		return ReqUrl(getEaseMobUrl() + "users", parameter, "POST", token);

	}

	/// <summary>
	/// 删除用户
	/// </summary>
	/// <param name="userName">账号</param>
	/// <returns>成功返回会员JSON详细信息，失败直接返回：系统错误信息</returns>
	public static String AccountDel(String userName) {
		return ReqUrl(getEaseMobUrl() + "users/" + userName, "", "DELETE", token);
	}

//	/**
//	 * 向指定 URL 发送POST方法的请求
//	 * 
//	 * @param url
//	 *            发送请求的 URL
//	 * @param param
//	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//	 * @return 所代表远程资源的响应结果
//	 */
//	public static String ReqUrl(String url, String param, String token) {
//		PrintWriter out = null;
//		BufferedReader in = null;
//		String result = "";
//		try {
//			URL realUrl = new URL(url);
//			// 打开和URL之间的连接
//			URLConnection conn = realUrl.openConnection();
//			// 设置通用的请求属性
//			conn.setRequestProperty("Content-Type", "application/json");
//			// conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible;
//			// MSIE 6.0; Windows NT 5.1;SV1)");
//			conn.setRequestProperty("Authorization", "Bearer " + token);
//			// 发送POST请求必须设置如下两行
//			conn.setDoOutput(true);
//			conn.setDoInput(true);
//			// 获取URLConnection对象对应的输出流
//			out = new PrintWriter(conn.getOutputStream());
//			// 发送请求参数
//			out.print(param);
//			// flush输出流的缓冲
//			out.flush();
//			// 定义BufferedReader输入流来读取URL的响应
//			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			String line;
//			while ((line = in.readLine()) != null) {
//				result += line;
//			}
//		} catch (Exception e) {
//			System.out.println("发送 POST 请求出现异常！" + e);
//			e.printStackTrace();
//		}
//		// 使用finally块来关闭输出流、输入流
//		finally {
//			try {
//				if (out != null) {
//					out.close();
//				}
//				if (in != null) {
//					in.close();
//				}
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return result;
//	}

	/**
	 * DELETE
	 * 
	 * @param url1
	 * @param param
	 * @param token
	 * @return
	 */
	public static String ReqUrl(String url1, String param, String method, String token) {
		try {
			URL url = new URL(url1);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Authorization", "Bearer " + token);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
			// 将要传递的集合转换成JSON格式
			// 组织要传递的参数
			out.write(param);
			out.flush();
			out.close();
			// 获取返回的数据
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = null;
			StringBuffer content = new StringBuffer();
			while ((line = in.readLine()) != null) {
				// line 为返回值，这就可以判断是否成功
				content.append(line);
			}
			in.close();
			return content.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";

	}

}
