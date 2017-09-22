package com.api.utils.decrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.myErp.utils.CommonMethod;

/**
 * 
 * @author sdcuike
 *
 *         Created At 2016年10月26日 下午11:53:32
 */
public class DecryptEncryptFastJsonHttpMessageConverter extends GsonHttpMessageConverter implements InitializingBean {

	private RequestDecryptResponseEncryptBodyProcessor requestDecryptResponseEncryptBodyProcessor;

	public void setRequestDecryptResponseEncryptBodyProcessor(
			RequestDecryptResponseEncryptBodyProcessor requestDecryptResponseEncryptBodyProcessor) {
		this.requestDecryptResponseEncryptBodyProcessor = requestDecryptResponseEncryptBodyProcessor;
	}

	public RequestDecryptResponseEncryptBodyProcessor getRequestDecryptResponseEncryptBodyProcessor() {
		return requestDecryptResponseEncryptBodyProcessor;
	}

	public DecryptEncryptFastJsonHttpMessageConverter() {
		super();
	}

	@Override
	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		TypeToken<?> token = getTypeToken(type);
		return this.readTypeToken(token, inputMessage);
	}

	private Gson gson = new Gson();
	private String jsonPrefix;

	private Object readTypeToken(TypeToken<?> token, HttpInputMessage inputMessage) throws IOException {
		Reader json = new InputStreamReader(inputMessage.getBody(), getCharset(inputMessage.getHeaders()));
		try {
			return gson.fromJson(json, token.getType());
		} catch (JsonParseException ex) {
			throw new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}

	private Charset getCharset(HttpHeaders headers) {
		if (headers == null || headers.getContentType() == null || headers.getContentType().getCharset() == null) {
			return DEFAULT_CHARSET;
		}
		return headers.getContentType().getCharset();
	}

	@Override
	protected void writeInternal(Object o, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		Charset charset = getCharset(outputMessage.getHeaders());

		OutputStreamWriter writer = new OutputStreamWriter(outputMessage.getBody(), charset);
		try {
			if (this.jsonPrefix != null) {
				writer.append(this.jsonPrefix);
			} else 
			{
				// 此处加密!!!!!!!!!!!!!!!!!!!!!!!!!!
				String reqStr = this.gson.toJson(o);
				reqStr = requestDecryptResponseEncryptBodyProcessor.encryptResponseBody(reqStr);
				writer.write(reqStr);
			}
			writer.close();
		} catch (JsonIOException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
	}
	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		TypeToken<?> token = getTypeToken(clazz);
		return readTypeToken(token, inputMessage);

	}

	@Resource
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

	@Override
	public void afterPropertiesSet() throws Exception {
		// 重新排列处理方法，不然map会先处理，从而加密不了数据
		List<HandlerMethodReturnValueHandler> handlers = new ArrayList<HandlerMethodReturnValueHandler>(
				requestMappingHandlerAdapter.getReturnValueHandlers());

		int requestResponseBodyMethodProcessorIndex = 0;
		int requestDecryptResponseEncryptBodyMethodProcessorIndex = 0;
		RequestDecryptResponseEncryptBodyMethodProcessor requestDecryptResponseEncryptBodyMethodProcessor = null;
		for (int i = 0, length = handlers.size(); i < length; i++) {
			HandlerMethodReturnValueHandler handler = handlers.get(i);
			if (handler instanceof RequestDecryptResponseEncryptBodyMethodProcessor) {
				requestDecryptResponseEncryptBodyMethodProcessor = (RequestDecryptResponseEncryptBodyMethodProcessor) handler;
				requestDecryptResponseEncryptBodyMethodProcessorIndex = i;
			} else if (handler instanceof RequestResponseBodyMethodProcessor) {
				requestResponseBodyMethodProcessorIndex = i;
			}

		}

		if (requestDecryptResponseEncryptBodyMethodProcessor != null) {
			handlers.remove(requestDecryptResponseEncryptBodyMethodProcessorIndex);
			handlers.add(requestResponseBodyMethodProcessorIndex + 1, requestDecryptResponseEncryptBodyMethodProcessor);
		}

		requestMappingHandlerAdapter.setReturnValueHandlers(handlers);

		//
		List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<HandlerMethodArgumentResolver>(
				requestMappingHandlerAdapter.getArgumentResolvers());
		RequestDecryptResponseEncryptBodyMethodProcessor requestDecryptResponseEncryptBodyMethodProcessor2 = null;
		for (int i = 0, length = argumentResolvers.size(); i < length; i++) {
			HandlerMethodArgumentResolver argumentResolver = argumentResolvers.get(i);
			if (argumentResolver instanceof RequestDecryptResponseEncryptBodyMethodProcessor) {
				requestDecryptResponseEncryptBodyMethodProcessor2 = (RequestDecryptResponseEncryptBodyMethodProcessor) argumentResolver;
				requestDecryptResponseEncryptBodyMethodProcessorIndex = i;
			} else if (argumentResolver instanceof RequestResponseBodyMethodProcessor) {
				requestResponseBodyMethodProcessorIndex = i;
			}
		}

		if (requestDecryptResponseEncryptBodyMethodProcessor2 != null) {
			argumentResolvers.remove(requestDecryptResponseEncryptBodyMethodProcessorIndex);
			argumentResolvers.add(requestResponseBodyMethodProcessorIndex + 1,
					requestDecryptResponseEncryptBodyMethodProcessor2);
		}

		requestMappingHandlerAdapter.setArgumentResolvers(argumentResolvers);

	}

}