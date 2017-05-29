package creativei.service;

import creativei.entity.OrderItem;
import creativei.enums.OrderItemState;

import java.util.List;

/**
 * Created by Aayush on 5/29/2017.
 */
public interface OrderItemService {
    public List<OrderItem> findOrderItemByState(OrderItemState orderItemState);
    public OrderItem findOrderItem(Long id) throws Exception;
    public OrderItem updateOrderItem(OrderItem orderItem);
    public OrderItem createOrderItem(OrderItem orderItem) throws Exception;
    public List<OrderItem> updateAll(List<OrderItem> items);
}
