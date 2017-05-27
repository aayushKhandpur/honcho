package creativei.dao;

import creativei.entity.Order;
import creativei.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 12-05-2017.
 */
public interface OrderItemDao extends JpaRepository<OrderItem, Long> {
}
