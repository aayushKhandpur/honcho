package creativei.service;

import creativei.entity.RestaurantTable;

import java.util.List;

/**
 * Created by Administrator on 16-04-2017.
 */
public interface RestaurantTableService {
    public List<RestaurantTable> getAll();
    public RestaurantTable getById(Long id);
    public RestaurantTable getByTableNumber(String tableNumber);
}
