package com.api.utils.decrypt;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;

/**
 * @author sdcuike
 *
 *         Created At 2016年10月27日 下午11:07:17
 */
public class RequestDecryptResponseEncryptBodyProcessorImpl extends RequestDecryptResponseEncryptBodyProcessor {

	 @Override
	    protected String doDecryptRequestBody(String input, HttpHeaders httpHeaders, Charset charset) {
	        return super.doDecryptRequestBody(input, httpHeaders, charset);
	    }

	    @Override
	    protected String doEncryptResponseBody(String input, HttpHeaders httpHeaders, Charset charset) {
	        return super.doEncryptResponseBody(input, httpHeaders, charset);
	    }
}
