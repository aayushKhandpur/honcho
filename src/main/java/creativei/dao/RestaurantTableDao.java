package creativei.dao;

import creativei.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 16-04-2017.
 */
@Transactional
public interface RestaurantTableDao extends JpaRepository<RestaurantTable, Long>{
    
}
