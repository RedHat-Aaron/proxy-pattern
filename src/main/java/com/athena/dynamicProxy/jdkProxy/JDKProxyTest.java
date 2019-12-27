package com.athena.dynamicProxy.jdkProxy;

import com.athena.dynamicProxy.Person;

/**
 * @Author: xiaoxiang.zhang
 * @Description:
 * @Date: Create in 6:52 PM 2019/11/26
 */
public class JDKProxyTest {
    public static void main(String[] args) {
        //这个代理模式实际上并不在意你穿入的是谁，相当于一套模版方法，只要让对应的对象实现接口，然后传入即可
        JDKMeipo jdkMeipo = new JDKMeipo(new Girl());
        Person person = jdkMeipo.getInstance();
        person.findLove();
    }
}
