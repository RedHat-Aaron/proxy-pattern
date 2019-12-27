package com.athena.dynamicProxy.gpProxy;

import java.lang.reflect.Method;

/**
 * @Author: xiaoxiang.zhang
 * @Description:
 * @Date: Create in 7:35 PM 2019/11/26
 */
public interface GPInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
