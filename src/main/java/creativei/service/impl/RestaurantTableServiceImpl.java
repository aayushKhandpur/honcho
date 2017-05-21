package creativei.service.impl;

import creativei.dao.RestaurantTableDao;
import creativei.entity.RestaurantTable;
import creativei.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 16-04-2017.
 */
@Service("RestaurantTableService")
public class RestaurantTableServiceImpl implements RestaurantTableService{

    private Map<Long, RestaurantTable> tableCache = new HashMap();

    @Autowired
    RestaurantTableDao restaurantTableDao;

    @Override
    public List<RestaurantTable> getAll() {
        List<RestaurantTable> tables = restaurantTableDao.findAll();
        updateTableCache(tables);
        return tables;
    }

    @Override
    public RestaurantTable getById(Long id) {
        RestaurantTable restaurantTable = tableCache.get(id);
        if(restaurantTable == null){
           restaurantTable =  restaurantTableDao.findOne(id);
           if (restaurantTable != null) tableCache.put(id, restaurantTable);
        }
        return restaurantTable;
    }

    @Override
    public RestaurantTable getByTableNumber(String tableNumber) {
        return null;
    }

    private void updateTableCache(List<RestaurantTable> tables){
        if(tables != null){
            for(RestaurantTable table : tables){
                this.tableCache.put(table.getId(), table);
            }
        }
    }
}
