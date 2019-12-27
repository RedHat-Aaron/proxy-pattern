package com.athena.dynamicProxy.jdkProxy;

import com.athena.dynamicProxy.Person;

/**
 * @Author: xiaoxiang.zhang
 * @Description:动态代理-被代理对象
 * @Date: Create in 6:53 PM 2019/11/26
 */
public class Girl implements Person {

    @Override
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("有钱");
        System.out.println("对我好");
    }
}
