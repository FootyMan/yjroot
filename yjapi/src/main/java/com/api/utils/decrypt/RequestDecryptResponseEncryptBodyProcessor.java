package com.api.utils.decrypt;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import com.api.utils.DecryptEncryptUtils;

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

	public final String encryptResponseBody(String input) {
		return doEncryptResponseBody(input);
	}

	protected String doDecryptRequestBody(String input, HttpHeaders httpHeaders, Charset charset) {
		return input;
	}

	/**
	 * Response加密操作
	 * 
	 * @param input
	 * @param httpHeaders
	 * @param charset
	 * @return
	 */
	protected String doEncryptResponseBody(String input) {
		String jiami = DecryptEncryptUtils.doEncryptResponseBody(input);
		return jiami;
	}

}
