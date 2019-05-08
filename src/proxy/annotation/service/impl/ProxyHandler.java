package proxy.annotation.service.impl;

import proxy.annotation.service.TransactionProxy;

/**
 * @Author: zouhongxue
 * @Date: 2019/5/7 9:57 AM
 */
public class ProxyHandler implements TransactionProxy {
    @Override
    public boolean before() {
        System.out.println("之前注入");
        return true;
    }

    @Override
    public void after() {
        System.out.println("之后注入");
    }
}
