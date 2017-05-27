package creativei.webapp;

import creativei.dao.RestaurantTableDao;
import creativei.dao.UserDao;
import creativei.entity.RestaurantTable;
import creativei.entity.User;
import creativei.manager.RestaurantTableManager;
import creativei.manager.impl.RestaurantManagerImpl;
import creativei.manager.impl.RestaurantTableManagerImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vo.Error;
import vo.Hello;
import vo.ResponseObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class RestaurantController {
    @Autowired
    UserDao userDao;
    @Autowired
    RestaurantTableDao restaurantTableDao;
    @Autowired
    RestaurantManagerImpl restaurantManager;
    @Autowired
    RestaurantTableManager restaurantTableManager;
    private static final Logger logger = LoggerFactory
            .getLogger(RestaurantController.class);

    @RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody ResponseObject autheticateUser(@RequestBody Map<String,String> credentials){
        //Dummy authentication
        ResponseObject vo;
        String userId = credentials.get("userId");
        String password = credentials.get("password");
        if("admin".equalsIgnoreCase(userId) && "admin".equalsIgnoreCase(password)){
            vo = restaurantManager.getRestaurant();
        }else{
            Error loginError = new Error("1001","Invalid login credentials");
            vo = new ResponseObject(loginError);
        }
        return vo;
    }
    @RequestMapping(value = "/restaurant/{id}", produces = "application/json", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject restaurant(@PathVariable String id, HttpServletRequest request) {
        logger.info("Request to get restaurant for id: " + id);
        Long restaurantId = 101L;
        return restaurantManager.getRestaurant();
    }

    @RequestMapping(value = "/restaurant/services", produces = "application/json", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject getservices(HttpServletRequest request) {
        Long restaurantId = 101L;
        return restaurantManager.getServices(restaurantId);
    }

    @RequestMapping(value = "/restaurant/tables", produces = "application/json", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject getTables(HttpServletRequest request) {
        return restaurantTableManager.getAll();
    }
}
