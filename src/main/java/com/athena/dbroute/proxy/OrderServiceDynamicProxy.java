package com.athena.dbroute.proxy;

import com.athena.dbroute.db.DynamicDataSourceEntity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xiaoxiang.zhang
 * @Description:订单服务的动态代理
 * @Date: Create in 4:20 PM 2019/11/27
 */
public class OrderServiceDynamicProxy implements InvocationHandler {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");

    private Object target;

    public OrderServiceDynamicProxy(Object target) {
        this.target = target;
    }

    /*
     * @Author xiangxz
     * @Description 产生一个代理对象
     * @Date 4:26 PM 2019/11/27
     **/

    public Object getInstance() {
        return Proxy.newProxyInstance(OrderServiceDynamicProxy.class.getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(args[0]);
        Object object = method.invoke(this.target, args);
        after();
        return object;
    }

    private void after() {
        DynamicDataSourceEntity.resetStore();
    }


    public void before(Object object) {
        try {
            Long time = (Long) object.getClass().getMethod("getCreateTime").invoke(object);
            Integer dbRouter = Integer.valueOf(simpleDateFormat.format(new Date(time)));
            System.out.println("动态代理自动分配到[DB_" + dbRouter + "]数据源处理数据");
            DynamicDataSourceEntity.set(dbRouter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
