package com.athena.dynamicProxy.gpProxy;

import com.athena.dynamicProxy.Person;

import java.lang.reflect.Method;

/**
 * @Author: xiaoxiang.zhang
 * @Description:动态代理-自行实现代理模式
 * @Date: Create in 7:47 PM 2019/11/26
 */
public class GPMeipo implements GPInvocationHandler {
    //1.首先要持有被代理人的引用
    private Person target;

    //2.对对象的引用进行赋值
    public GPMeipo(Person person) {
        this.target = person;
    }

    //3.创建代理对象
    public Person getInstance() {
        return (Person) GPProxy.newProxyInstance(new GPClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.target, args);
        after();
        return obj;
    }

    private void before() {
        System.out.println("我是媒婆，我已确认你的需求，开始给你寻找对象！");
    }

    private void after() {
        System.out.println("对象匹配完毕!");
    }
}
