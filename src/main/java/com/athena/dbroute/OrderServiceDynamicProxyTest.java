package com.athena.dbroute;

import com.athena.dbroute.proxy.OrderServiceDynamicProxy;

/**
 * @Author: xiaoxiang.zhang
 * @Description:订单服务动态代理测试类
 * @Date: Create in 4:39 PM 2019/11/27
 */
public class OrderServiceDynamicProxyTest {
    public static void main(String[] args) {
        OrderServiceDynamicProxy proxy = new OrderServiceDynamicProxy(new OrderServiceImpl());
        IOrderService orderService = (IOrderService)proxy.getInstance();
        Order order = new Order();
        order.setCreateTime(System.currentTimeMillis());
        orderService.createOrder(order);
    }
}
