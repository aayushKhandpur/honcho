package creativei.dao;

import creativei.entity.Order;
import creativei.enums.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Transactional
public interface OrderDao extends JpaRepository<Order, Long> {
    public List<Order> findByOrderState(OrderState orderState);
    public List<Order> findByIsActive(boolean isActive);


}
