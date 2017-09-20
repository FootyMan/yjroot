package com.api.requestresponse;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 返回json信息加密
 * 
 * @author sdcuike
 *
 *         Created At 2016年10月26日 下午8:54:08
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseEncryptBody {

}

