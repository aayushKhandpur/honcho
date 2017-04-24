package creativei.service.impl;

import creativei.dao.RestaurantTableDao;
import creativei.entity.RestaurantTable;
import creativei.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 16-04-2017.
 */
@Service("RestaurantTableService")
public class RestaurantTableServiceImpl implements RestaurantTableService{
    @Autowired
    RestaurantTableDao restaurantTableDao;

    @Override
    public List<RestaurantTable> getAll() {
        return restaurantTableDao.findAll();
    }

    @Override
    public RestaurantTable getById(Long id) {
        return null;
    }

    @Override
    public RestaurantTable getByTableNumber(String tableNumber) {
        return null;
    }
}
