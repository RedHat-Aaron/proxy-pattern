package com.athena.dynamicProxy.cglibProxy;

/**
 * @Author: xiaoxiang.zhang
 * @Description:Cglib测试类测试
 * @Date: Create in 10:51 PM 2019/11/27
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        CglibMeipo cglibMeipo = new CglibMeipo();
        Customer customer = (Customer) cglibMeipo.getInstance(Customer.class);
        customer.findLove();
    }
}
