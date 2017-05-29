package creativei.service.impl;

import creativei.dao.OrderItemDao;
import creativei.entity.OrderItem;
import creativei.enums.OrderItemState;
import creativei.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aayush on 5/29/2017.
 */
@Service("OrderItemService")
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    OrderItemDao orderItemDao;

    @Override
    public List<OrderItem> findOrderItemByState(OrderItemState orderItemState) {
        return null;
    }

    @Override
    public OrderItem findOrderItem(Long id) throws Exception {
        return null;
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemDao.save(orderItem);
    }



    @Override
    public OrderItem createOrderItem(OrderItem orderItem) throws Exception {
        return null;
    }

    @Override
    public List<OrderItem> updateAll(List<OrderItem> items) {
        return orderItemDao.save(items);
    }
}
