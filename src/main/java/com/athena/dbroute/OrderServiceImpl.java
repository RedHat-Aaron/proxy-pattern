package com.athena.dbroute;

/**
 * @Author: xiaoxiang.zhang
 * @Description:订单-业务层
 * @Date: Create in 4:44 PM 2019/11/26
 */
public class OrderServiceImpl implements IOrderService {

    private OrderDAO orderDAO;

    public OrderServiceImpl() {
        this.orderDAO = new OrderDAO();
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderServiceImpl调用orderDAO创建order对象");
        return orderDAO.insert(order);
    }
}
