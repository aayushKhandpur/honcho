package creativei.dao;

import creativei.entity.Category;
import creativei.entity.Order;
import creativei.enums.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Transactional
public interface CategoryDao extends JpaRepository<Category, Long> {
    public List<Category> findByName(String name);
}
