package com.api.requestresponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import com.api.utils.DES;
import com.myErp.utils.SystemConfig;

/**
 * @author sdcuike
 *
 *         Created At 2016年10月26日 下午11:59:07
 */
public abstract class RequestDecryptResponseEncryptBodyProcessor {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	public final String decryptRequestBody(HttpInputMessage inputMessage, Charset charset) throws IOException {
		InputStream inputStream = inputMessage.getBody();
		String input = IOUtils.toString(inputStream, charset);
		HttpHeaders httpHeaders = inputMessage.getHeaders();
		return doDecryptRequestBody(input, httpHeaders, charset);
	}

	public final String encryptResponseBody(String input, HttpHeaders httpHeaders, Charset charset) {
		return doEncryptResponseBody(input, httpHeaders, charset);
	}

	protected String doDecryptRequestBody(String input, HttpHeaders httpHeaders, Charset charset) {
		return input;
	}

	/**
	 * Response加密操作
	 * @param input
	 * @param httpHeaders
	 * @param charset
	 * @return
	 */
	protected String doEncryptResponseBody(String input, HttpHeaders httpHeaders, Charset charset) {
		String jiami = input;
		if (SystemConfig.isEncrypt) {
			try {
				jiami = DES.encrypt(input);
				System.out.println("加密后数据" + jiami);

				String jimi = DES.decrypt(jiami);
				System.out.println("解密后数据" + jimi);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return jiami;
	}

}
