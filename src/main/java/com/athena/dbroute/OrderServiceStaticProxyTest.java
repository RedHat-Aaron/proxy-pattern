package com.athena.dbroute;

import com.athena.dbroute.proxy.OrderServiceStaticProxy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xiaoxiang.zhang
 * @Description:订单服务静态代理测试类
 * @Date: Create in 5:01 PM 2019/11/26
 */
public class OrderServiceStaticProxyTest {
    public static void main(String[] args) {
        Order order = new Order();
        order.setCreateTime(System.currentTimeMillis());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse("2017/08/19");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setCreateTime(date.getTime());

        IOrderService orderService = new OrderServiceStaticProxy(new OrderServiceImpl());
        orderService.createOrder(order);
    }
}
