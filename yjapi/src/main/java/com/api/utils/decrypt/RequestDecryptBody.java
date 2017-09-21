package com.api.utils.decrypt;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * json解密后的消息
 * 
 * @author sdcuike
 *
 *         Created At 2016年10月26日 下午8:56:27
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestDecryptBody {

}
