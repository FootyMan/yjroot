package com.api.utils;

import java.net.URLEncoder;

import com.myErp.utils.MandDaoClient;

public class PhoneMessageSend {

	/**
	 * 注册短信发送
	 * 
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public boolean RegisteMessage(String phone, String code) throws Exception {
		MandDaoClient client = new MandDaoClient();
		String content = URLEncoder.encode(MandDaoClient.sign + "欢迎加入欲见，验证码为" + code + "，一分钟内有效", "utf8");
		String result_mt = client.mdSmsSend_u(phone, content, "", "", "");
		if (result_mt.startsWith("-") || result_mt.equals(""))// 发送短信，如果是以负号开头就是发送失败。
		{
			System.out.print("发送失败！返回值为：" + result_mt + "请查看webservice返回值对照表");
			return false;
		}
		return true;
	}
}
