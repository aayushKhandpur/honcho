package creativei.webapp;

import creativei.entity.Order;
import creativei.entity.OrderItem;
import creativei.enums.OrderState;
import creativei.manager.impl.OrderManagerImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import vo.ResponseObject;
import vo.modal.OrderItemVo;
import vo.modal.OrderVo;

import java.util.ArrayList;
import java.util.List;

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
        OrderState orderState =OrderState.valueOf(state);
        return orderManager.finOrderByState(orderState);
    }

    @RequestMapping(value = "/orders/active", produces = "application/json", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject activeOrders(HttpServletRequest request) {
        logger.info("Request to get active orders");
        boolean isActive = true;
        return orderManager.findOrderByIsActive(isActive);
    }

    @RequestMapping(value = "/order/{id}", produces = "application/json", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject order(@PathVariable String id, HttpServletRequest request) {
        logger.info("Request to get order by id: " + id);
        Long orderId = Long.parseLong(id);
        return orderManager.getById(orderId);
    }

    @RequestMapping(value = "/order/save", produces = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject saveOrder(@RequestBody OrderVo orderVo, HttpServletRequest request) {
        logger.info("Request to save order.");
        Order order = new Order(orderVo);
        order.setOrderItemList(getOrderItems(orderVo.getItems(), order));
        return orderManager.createOrder(order);
    }

    private List<OrderItem> getOrderItems(List<OrderItemVo> orderItemVos, Order order){
        if(orderItemVos!=null && !orderItemVos.isEmpty()){
            List<OrderItem> orderItems = new ArrayList();
            for(OrderItemVo orderItemVo : orderItemVos){
                OrderItem item = new OrderItem(orderItemVo);
                item.setOrder(order);
                orderItems.add(item);
            }
            return orderItems;
        }
        return new ArrayList();
    }

}
