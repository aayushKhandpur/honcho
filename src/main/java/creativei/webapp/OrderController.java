package creativei.webapp;

import creativei.manager.impl.OrderManagerImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vo.ResponseObject;

/**
 * Created by Administrator on 26-03-2017.
 */
@RestController
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderManagerImpl orderManager;

    @RequestMapping(value = "/orders/{state}", produces = "application/json", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject orders(@PathVariable String state, HttpServletRequest request) {
        logger.info("Request to get orders for state: " + state);
        return orderManager.getAllByState(state);
    }

    @RequestMapping(value = "/order/{id}", produces = "application/json", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject order(@PathVariable String id, HttpServletRequest request) {
        logger.info("Request to get order by id: " + id);
        Long orderId = Long.parseLong(id);
        return orderManager.getById(orderId);

    }

}
