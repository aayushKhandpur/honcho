package creativei.webapp;

import creativei.dao.UserDao;
import creativei.entity.User;
import creativei.manager.RestaurantTableManager;
import creativei.manager.impl.RestaurantManagerImpl;
import creativei.manager.impl.RestaurantTableManagerImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vo.Hello;
import vo.ResponseObject;

@RestController
public class RestaurantController {
    @Autowired
    UserDao userDao;
    @Autowired
    RestaurantManagerImpl restaurantManager;
    @Autowired
    RestaurantTableManager restaurantTableManager;
    private static final Logger logger = LoggerFactory
            .getLogger(RestaurantController.class);

    @RequestMapping("/hello")
    public
    @ResponseBody
    User home() {
        Hello h = new Hello();
        h.setId(1L);
        h.setName("aayush");
        User user = new User(h.getName());
        user = userDao.save(user);
        return user;
    }

    @RequestMapping(value = "/restaurant/{id}", produces = "application/json", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject restaurant(@PathVariable String id, HttpServletRequest request) {
        logger.info("Request to get restaurant for id: " + id);
        Long restaurantId = 101L;
        return restaurantManager.getRestaurant(restaurantId);
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
