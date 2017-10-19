package com.api.utils;

import com.service.utils.SystemConfig;

public class DecryptEncryptUtils {

	/**
	 * 加密
	 * @param input
	 * @return
	 */
	public static String doEncryptResponseBody(String input) {
		String jiami = input;
		if (SystemConfig.isEncrypt) {
			try {
				jiami = DES.encrypt(input);
				// System.out.println("加密后数据" + jiami);
				//
				// String jimi = DES.decrypt(jiami);
				// System.out.println("解密后数据" + jimi);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return jiami;
	}
}
