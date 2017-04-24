package creativei.service.impl;

import creativei.entity.Order;
import creativei.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> finOrderByState() {
        return null;
    }

    @Override
    public Order findOrder() {
        return null;
    }

    @Override
    public Order updateOrder() {
        return null;
    }

    @Override
    public Order createOrder() {
        return null;
    }
}
