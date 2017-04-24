package creativei.service;

import creativei.entity.Order;

import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
public interface OrderService {
    public List<Order> finOrderByState();
    public Order findOrder();
    public Order updateOrder();
    public Order createOrder();

}
