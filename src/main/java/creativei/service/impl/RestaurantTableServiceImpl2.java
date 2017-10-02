package creativei.service.impl;

import creativei.entity.RestaurantTable;
import creativei.service.RestaurantTableService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aayush on 9/30/2017.
 */
@Service("RestaurantTableService1")
public class RestaurantTableServiceImpl2 implements RestaurantTableService{
    @Override
    public List<RestaurantTable> getAll() {
        return null;
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
