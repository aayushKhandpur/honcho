package creativei.manager.impl;

import creativei.entity.RestaurantTable;
import creativei.manager.RestaurantTableManager;
import creativei.service.RestaurantTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vo.Error;
import vo.ResponseObject;

import java.util.List;

/**
 * Created by Administrator on 16-04-2017.
 */
@Service("RestaurantTableManager")
public class RestaurantTableManagerImpl implements RestaurantTableManager{
    private static final Logger logger = LoggerFactory
            .getLogger(RestaurantTableManagerImpl.class);
    @Autowired
    RestaurantTableService restaurantTableService;

    @Override
    public ResponseObject getAll() {
        List<RestaurantTable> restaurantTableList = restaurantTableService.getAll();
        if(restaurantTableList == null || restaurantTableList.isEmpty()){
            Exception e = new Exception("No tables found for the restaurant.");
            logger.error(e.getMessage(), e);
            Error error =  new Error("1001", e.getMessage());
            return new ResponseObject(error);
        }
        return new ResponseObject(restaurantTableList);
    }

    @Override
    public ResponseObject getById(Long id) {
        return null;
    }

    @Override
    public ResponseObject getByTableNumber(String name) {
        return null;
    }
}
