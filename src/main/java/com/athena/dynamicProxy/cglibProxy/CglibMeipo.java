package com.athena.dynamicProxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: xiaoxiang.zhang
 * @Description:cglib的动态代理
 * @Date: Create in 4:17 PM 2019/11/27
 */
public class CglibMeipo implements MethodInterceptor {

    //3.创建实例获取方法
    public Object getInstance(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object obj = methodProxy.invokeSuper(o, objects);
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
