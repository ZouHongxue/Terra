package proxy.annotation.service;

import proxy.annotation.enumeration.ProxyType;
import proxy.annotation.intercepter.ProxyAnnotation;

/**
 * @Author: zouhongxue
 * @Date: 2019/5/7 9:55 AM
 */
public interface ActionService {
    @ProxyAnnotation(ProxyType.AFTER)
    void run();
    @ProxyAnnotation(ProxyType.BEFORE)
    void say();
    @ProxyAnnotation(ProxyType.ALL)
    void eat();
}
