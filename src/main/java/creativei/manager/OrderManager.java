package creativei.manager;

import creativei.entity.Order;
import creativei.enums.OrderState;
import vo.ResponseObject;

import java.util.List;

/**
 * Created by Administrator on 29-04-2017.
 */
public interface OrderManager {
    public ResponseObject finOrderByState(OrderState orderState);
    public ResponseObject findOrder(Long id);
    public ResponseObject updateOrder(Order order);
    public ResponseObject createOrder(Order order);
    public ResponseObject findOrderByIsActive(boolean isActive);
}
