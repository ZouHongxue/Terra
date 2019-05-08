package proxy.annotation.service.impl;

import proxy.annotation.service.ActionService;

/**
 * @Author: zouhongxue
 * @Date: 2019/5/7 10:00 AM
 */
public class ActionServiceImpl implements ActionService {

    @Override
    public void run() {
        System.out.println("who's running");
    }

    @Override
    public void say() {
        System.out.println("who's talking");
    }

    @Override
    public void eat() {
        System.out.println("who's eating");
    }
}
