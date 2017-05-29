package creativei.webapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import creativei.entity.Category;
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
import vo.Error;
import vo.ResponseObject;
import vo.modal.OrderItemVo;
import vo.modal.OrderVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@RestController 
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final ObjectMapper mapper = new ObjectMapper();
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
    ResponseObject saveOrder(@RequestBody String orderStr, HttpServletRequest request) {
        logger.info("Request to save order.");
        try {
            OrderVo orderVo = mapper.readValue(orderStr, new TypeReference<OrderVo>(){});
            Order order = new Order(orderVo);
            order.setOrderItemList(getOrderItems(orderVo.getItems(), order));
            return orderManager.createOrder(order);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            Error er = new Error("1001", e.getMessage());
            ResponseObject ro = new ResponseObject(er);
            ro.setStatus(ResponseObject.ResponseStatus.ERROR);
            return ro;
        }

    }

    @RequestMapping(value = "/order/update", produces = "application/json", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseObject updateOrder(@RequestBody String orderStr, HttpServletRequest request) {
        logger.info("Request to save order.");
        try {
            OrderVo orderVo = mapper.readValue(orderStr, new TypeReference<OrderVo>(){});
            Order order = new Order(orderVo);
            order.setOrderItemList(getOrderItems(orderVo.getItems(), order));
            return orderManager.updateOrder(order);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            Error er = new Error("1001", e.getMessage());
            ResponseObject ro = new ResponseObject(er);
            ro.setStatus(ResponseObject.ResponseStatus.ERROR);
            return ro;
        }

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
