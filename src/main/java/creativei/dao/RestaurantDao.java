package creativei.dao;

import creativei.entity.Restaurant;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 26-03-2017.
 */
@Transactional
public interface RestaurantDao extends CrudRepository<Restaurant, Long>{

}
