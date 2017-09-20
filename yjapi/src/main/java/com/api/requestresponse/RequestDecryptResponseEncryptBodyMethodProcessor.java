package com.api.requestresponse;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * 自定义注解处理器
 * 
 * @author sdcuike
 *
 *         Created At 2016年10月26日 下午9:44:25
 */
public class RequestDecryptResponseEncryptBodyMethodProcessor extends RequestResponseBodyMethodProcessor {

//   private final Logger log = LoggerFactory.getLogger(getClass());

    public RequestDecryptResponseEncryptBodyMethodProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestDecryptBody.class);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseEncryptBody.class) ||
                returnType.hasMethodAnnotation(ResponseEncryptBody.class));
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
//        log.info("RequestURI:{}", webRequest.getNativeRequest(HttpServletRequest.class).getRequestURI());
        super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }

    @Override
    protected <T> Object readWithMessageConverters(NativeWebRequest webRequest, MethodParameter methodParam, Type paramType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {
//        log.info("RequestURI:{}", webRequest.getNativeRequest(HttpServletRequest.class).getRequestURI());
        return super.readWithMessageConverters(webRequest, methodParam, paramType);
    }

}
