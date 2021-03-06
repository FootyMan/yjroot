package com.service.easemob;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.service.utils.SystemConfig;

/***
 * 网易云im
 * 
 * @author HCY
 *
 */
public class NeteaseBusiness {

	/// String url = "https://api.netease.im/nimserver/user/create.action";

	/**
	 * 创建accid
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static NeteaseModel CreateaccId(String easemobId,int registerId) {
		String jsonRet;
		try {
			String url = "https://api.netease.im/nimserver/user/create.action";
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("accid", easemobId));
			String token = registerId + SystemConfig.EaseSuffixId;
			nvps.add(new BasicNameValuePair("token", token));
			jsonRet = PostRequest(url, nvps);
		} catch (Exception e) {
			return null;
		}
		return JsonResolve(jsonRet);
	}

	/**
	 * 更新并获取新token
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static NeteaseModel RefreshToken(String userId) {
		String jsonRet;
		try {
			String url = "https://api.netease.im/nimserver/user/refreshToken.action";
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("accid", userId));
			jsonRet = PostRequest(url, nvps);
		} catch (Exception e) {
			return null;
		}
		return JsonResolve(jsonRet);
	}

	/**
	 * 禁用某个网易云通信ID
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static NeteaseModel BlockaccId(String userId) throws Exception {
		String url = "https://api.netease.im/nimserver/user/block.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", userId));
		String jsonRet = PostRequest(url, nvps);
		return JsonResolve(jsonRet);
	}

	/**
	 * 解禁被封禁的网易云通信ID
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static NeteaseModel UnblockaccId(String userId) throws Exception {
		String url = "https://api.netease.im/nimserver/user/unblock.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", userId));
		String jsonRet = PostRequest(url, nvps);
		return JsonResolve(jsonRet);
	}

	/**
	 * 轮询创建accid
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static NeteaseModel RollCreateaccId(String userId, String nickName, String icon) {
		String jsonRet;
		try {
			String url = "https://api.netease.im/nimserver/user/create.action";
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("accid", userId));
			nvps.add(new BasicNameValuePair("name", nickName));
			nvps.add(new BasicNameValuePair("icon", icon));
			jsonRet = PostRequest(url, nvps);
		} catch (Exception e) {
			return null;
		}
		return JsonResolve(jsonRet);
	}

	/**
	 * 更新用户名片
	 * 
	 * @param userId
	 * @param nickName
	 * @param icon
	 * @return
	 * @throws Exception
	 */
	public static NeteaseModel UpdateUinfo(String userId, String nickName, String icon) {
		String jsonRet;
		try {
			String url = "https://api.netease.im/nimserver/user/updateUinfo.action";
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("accid", userId));
			nvps.add(new BasicNameValuePair("name", nickName));
			nvps.add(new BasicNameValuePair("icon", icon));
			jsonRet = PostRequest(url, nvps);

		} catch (Exception e) {
			return null;
		}
		return JsonResolve(jsonRet);
	}

	/**
	 * 请求
	 * 
	 * @param url路径
	 * @param nvps参数
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static String PostRequest(String url, List<NameValuePair> nvps) throws Exception, Exception {

		// String url = "https://api.netease.im/nimserver/user/create.action";
		HttpPost httpPost = new HttpPost(url);
		String appKey = "dfe5724d830c73e04fea28d7ffbde313";
		String appSecret = "8822033395ae";
		String nonce = "12345";
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);// 参考
																					// 计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 执行请求
		// HttpResponse response = httpClient.execute(httpPost);
		CloseableHttpResponse response = httpclient.execute(httpPost);

		// 打印执行结果
		return EntityUtils.toString(response.getEntity(), "utf-8");
	}

	/**
	 * 解析返回结果
	 * 
	 * @param json
	 * @return
	 */
	public static NeteaseModel JsonResolve(String json) {
		Gson gson = new Gson();

		NeteaseModel netease = gson.fromJson(json, NeteaseModel.class);
		return netease;
	}
}
