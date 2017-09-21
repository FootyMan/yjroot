package com.api.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.myErp.utils.MandDaoClient;

public class PhoneMessageSend {

	/**
	 * 发送短信
	 * @param phone
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public boolean SendPhooneMsg(String phone, String msg) throws Exception {
		MandDaoClient client = new MandDaoClient();
		String content = URLEncoder.encode(MandDaoClient.sign + msg, "utf8");
		String result_mt = client.mdSmsSend_u(phone, content, "", "", "");
		if (result_mt.startsWith("-") || result_mt.equals(""))// 发送短信，如果是以负号开头就是发送失败。
		{
			System.out.print("发送失败！返回值为：" + result_mt + "请查看webservice返回值对照表");
			return false;
		}
		return true;
	}
}
