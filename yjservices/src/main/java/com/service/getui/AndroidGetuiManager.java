package com.service.getui;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

public class AndroidGetuiManager {

	// 您应用的appkey
	public static String appkey = "";
	// 您应用的mastersecret
	public static String masterSecret = "KFDNBNKAVj9bgykwvqgeA5";
	// 您应用的appid
	public static String appId = "TxzlIyCcfS9KuENjjP4ux1";
	// host
	public static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	/**
	 * 单个推送
	 */
	public static void PushtoSingle(String cid, String content) {
		IGtPush push = new IGtPush(host, appkey, masterSecret);
		LinkTemplate template = linkTemplateDemo(content);
		SingleMessage message = new SingleMessage();
		message.setData(template);
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 3600 * 1000);
		message.setData(template);
		// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
		message.setPushNetWorkType(0);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(cid);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			e.printStackTrace();
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
		} else {
			System.out.println("服务器响应异常");
		}
	}

	/**
	 * 一个appkey下的所有用户推送
	 * 
	 * @param content
	 */
	public static void PushtoAPP(String content) {
		IGtPush push = new IGtPush(host, appkey, masterSecret);

		LinkTemplate template = linkTemplateDemo(content);
		AppMessage message = new AppMessage();
		message.setData(template);

		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 推送给App的目标用户需要满足的条件
		AppConditions cdt = new AppConditions();
		List<String> appIdList = new ArrayList<String>();
		appIdList.add(appId);
		message.setAppIdList(appIdList);
		// 手机类型
		List<String> phoneTypeList = new ArrayList<String>();
		// 省份
		List<String> provinceList = new ArrayList<String>();
		// 自定义tag
		List<String> tagList = new ArrayList<String>();

		cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
		cdt.addCondition(AppConditions.REGION, provinceList);
		cdt.addCondition(AppConditions.TAG, tagList);
		message.setConditions(cdt);

		IPushResult ret = push.pushMessageToApp(message, "欲见app");
		System.out.println(ret.getResponse().toString());
	}

	/**
	 * 设置标题和内容
	 * 
	 * @return
	 */
	public static LinkTemplate linkTemplateDemo(String content) {
		LinkTemplate template = new LinkTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appkey);
		// 设置通知栏标题与内容
		template.setTitle("欲见");
		template.setText(content);
		// 配置通知栏图标
		// template.setLogo("icon.png");
		// 配置通知栏网络图标，填写图标URL地址
		// template.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		// template.setIsRing(true);
		// template.setIsVibrate(true);
		// template.setIsClearable(true);
		// 设置打开的网址地址
		// template.setUrl("http://www.baidu.com");
		return template;
	}
}
