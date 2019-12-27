package com.athena.dynamicProxy.jdkProxy;

import com.athena.dynamicProxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: xiaoxiang.zhang
 * @Description:动态代理-媒婆
 * @Date: Create in 6:53 PM 2019/11/26
 */
public class JDKMeipo implements InvocationHandler {

    //1.首先要持有被代理人的引用
    private Person target;

    //2.对对象的引用进行赋值
    public JDKMeipo(Person person) {
        this.target = person;
    }

    //3.创建代理对象
    public Person getInstance() {
        return (Person) Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
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
