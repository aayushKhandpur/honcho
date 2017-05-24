package creativei.service;

import creativei.entity.Order;
import creativei.enums.OrderState;

import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
public interface OrderService {
    public List<Order> findOrderByState(OrderState orderState);
    public Order findOrder(Long id) throws Exception;
    public Order updateOrder(Order order);
    public Order createOrder(Order order) throws Exception;
    public List<Order> findOrderByIsActive(boolean isActive);

}
