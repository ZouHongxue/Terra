package proxy.annotation.service.impl;

import proxy.annotation.enumeration.ProxyType;
import proxy.annotation.intercepter.ProxyAnnotation;
import proxy.annotation.service.TransactionProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: zouhongxue
 * @Date: 2019/5/6 7:21 PM
 */
public class AnnotationInvocationHandlerImpl implements InvocationHandler {

    private Object target;
    private TransactionProxy transactionProxy;

    AnnotationInvocationHandlerImpl(Object target, TransactionProxy transactionProxy) {
        this.target = target;
        this.transactionProxy = transactionProxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ProxyAnnotation annotation = method.getDeclaredAnnotation(ProxyAnnotation.class);
        Object result = null;
        if (annotation.value() == ProxyType.BEFORE) {
            if (transactionProxy.before()) {
                result = method.invoke(target, args);
            }
        } else if (annotation.value() == ProxyType.AFTER) {
            result = method.invoke(target, args);
            transactionProxy.after();
        } else if (annotation.value() == ProxyType.ALL) {
            transactionProxy.before();
            result = method.invoke(target, args);
            transactionProxy.after();
        }
        return result;
    }
}
