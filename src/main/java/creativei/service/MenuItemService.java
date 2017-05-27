package creativei.service;
import creativei.entity.MenuItem;

import java.util.List;

/**
 * Created by Administrator on 17-05-2017.
 */
public interface MenuItemService {
    public List<MenuItem> getAll();
    public MenuItem getById(Long id);
    public MenuItem getByName(String name);
}
