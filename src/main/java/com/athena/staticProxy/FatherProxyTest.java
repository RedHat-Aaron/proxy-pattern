package com.athena.staticProxy;

/**
 * @Author: xiaoxiang.zhang
 * @Description:静态代理-父类测试类
 * @Date: Create in 4:13 PM 2019/11/26
 */
public class FatherProxyTest {
    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findLove();

        //大家每天都在使用的一种静态代理的形式
        //对数据库进行分库分表
        //ThreadLocal
        //进行数据源动态切换

    }

}
