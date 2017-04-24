package creativei.dao.impl;

import creativei.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 26-03-2017.
 */
@Transactional
public interface OrderDao extends JpaRepository<Order, Long> {

}
