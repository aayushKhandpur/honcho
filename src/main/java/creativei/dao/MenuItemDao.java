package creativei.dao;

import creativei.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 01-05-2017.
 */
@Transactional
public interface MenuItemDao extends JpaRepository<MenuItem, Long> {
}
