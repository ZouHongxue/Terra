package proxy.annotation.service.impl;

import proxy.annotation.service.TransactionProxy;

import java.lang.reflect.Proxy;

/**
 * @Author: zouhongxue
 * @Date: 2019/5/6 7:28 PM
 */
public class ProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public T getProxyObject(T target, TransactionProxy transactionProxy) {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new AnnotationInvocationHandlerImpl(target, transactionProxy));
    }
}
