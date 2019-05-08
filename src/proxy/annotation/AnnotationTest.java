package proxy.annotation;

import proxy.annotation.service.ActionService;
import proxy.annotation.service.impl.ActionServiceImpl;
import proxy.annotation.service.impl.ProxyFactory;
import proxy.annotation.service.impl.ProxyHandler;

/**
 * @Author: zouhongxue
 * @Date: 2019/5/7 9:57 AM
 */
public class AnnotationTest {

    public static void main(String[] args) {
        ActionService actionService = new ProxyFactory<ActionServiceImpl>().getProxyObject(new ActionServiceImpl(), new ProxyHandler());
        actionService.say();
        actionService.run();
        actionService.eat();
    }
}
