package com.athena.dbroute.proxy;

import com.athena.dbroute.IOrderService;
import com.athena.dbroute.Order;
import com.athena.dbroute.db.DynamicDataSourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xiaoxiang.zhang
 * @Description:订单服务类的静态代理
 * @Date: Create in 4:53 PM 2019/11/26
 */
public class OrderServiceStaticProxy implements IOrderService {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");

    private IOrderService orderService;

    public OrderServiceStaticProxy(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public int createOrder(Order order) {
        Long time = order.getCreateTime();
        Integer dbRouter = Integer.valueOf(simpleDateFormat.format(new Date(time)));
        System.out.println("静态代理自动分配到[DB_" + dbRouter + "]数据源处理数据");
        DynamicDataSourceEntity.set(dbRouter);
        this.orderService.createOrder(order);
        DynamicDataSourceEntity.resetStore();
        return 0;
    }
}
