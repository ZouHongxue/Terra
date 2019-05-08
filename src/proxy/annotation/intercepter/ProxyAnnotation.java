package proxy.annotation.intercepter;

import proxy.annotation.enumeration.ProxyType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zouhongxue
 * @Date: 2019/5/6 7:17 PM
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxyAnnotation {
    ProxyType value() default ProxyType.ALL;
}
