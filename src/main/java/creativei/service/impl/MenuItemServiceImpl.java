package creativei.service.impl;

import creativei.dao.MenuItemDao;
import creativei.entity.MenuItem;
import creativei.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 17-05-2017.
 */
@Service("MenuItemService")
public class MenuItemServiceImpl implements MenuItemService {
    private Map<Long, MenuItem> menuItemCache = new HashMap();

    @Autowired
    MenuItemDao menuItemDao;

    @Override
    public List<MenuItem> getAll() {
        return null;
    }

    @Override
    public MenuItem getById(Long id) {
        MenuItem menuItem = menuItemCache.get(id);
        if(menuItem == null){
            menuItem = menuItemDao.findOne(id);
            if(menuItem != null) menuItemCache.put(id, menuItem);
        }
        return menuItem;
    }

    @Override
    public MenuItem getByName(String name) {
        return null;
    }

    private void populateMenuItemCache(List<MenuItem> menuItems){
        if(menuItems == null) return;
        for (MenuItem menuItem : menuItems){
            menuItemCache.put(menuItem.getId(), menuItem);
        }
    }

}
