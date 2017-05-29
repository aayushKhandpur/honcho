package creativei.manager.impl;

import creativei.entity.Order;
import creativei.enums.OrderState;
import creativei.manager.OrderManager;
import creativei.service.OrderService;
import creativei.webapp.CategoryController;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vo.Error;
import vo.ResponseObject;
import vo.response.ActiveOrderResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Service
public class OrderManagerImpl implements OrderManager {
    @Autowired
    OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderManagerImpl.class);

    public ResponseObject getById(Long id) {
        ResponseObject responseObject = null;
        try {
            Order order = orderService.findOrder(id);
            responseObject = new ResponseObject(getActiveOrderJson(order));
            responseObject.setStatus(ResponseObject.ResponseStatus.SUCCESS);
        }catch (Exception ex) {
            Error error = new Error("1001", ex.getMessage());
            responseObject = new ResponseObject(error);
            responseObject.setStatus(ResponseObject.ResponseStatus.ERROR);
        }finally {
            return responseObject;
        }
    }

    @Override
    public ResponseObject finOrderByState(OrderState orderState) {
        ResponseObject responseObject;
        try {
            if (orderState == null) throw new NullPointerException("Order state is null");
            List<Order> orders = orderService.findOrderByState(orderState);
            responseObject = new ResponseObject(orders);
        } catch (NullPointerException nex) {
            Error error = new Error("1001", nex.getMessage());
            responseObject = new ResponseObject(error);
            responseObject.setStatus(ResponseObject.ResponseStatus.ERROR);
        } catch (Exception ex) {
            Error error = new Error("1001", ex.getMessage());
            responseObject = new ResponseObject(error);
            responseObject.setStatus(ResponseObject.ResponseStatus.ERROR);
        }
        return responseObject;
    }

    @Override
    public ResponseObject findOrder(Long id) {
        return null;
    }

    @Override
    public ResponseObject updateOrder(Order order) {
        ResponseObject responseObject = null;
        try {
            order = orderService.updateOrder(order);
            responseObject = new ResponseObject(getActiveOrderJson(order));
            responseObject.setStatus(ResponseObject.ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            Error error = new Error("1001", ex.getMessage());
            responseObject = new ResponseObject(error);
            responseObject.setStatus(ResponseObject.ResponseStatus.ERROR);
        } finally {
            return responseObject;
        }
    }

    @Override
    public ResponseObject createOrder(Order order) {
        ResponseObject responseObject = null;
        try {
            order = orderService.createOrder(order);
            responseObject = new ResponseObject(getActiveOrderJson(order));
            responseObject.setStatus(ResponseObject.ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            Error error = new Error("1001", ex.getMessage());
            responseObject = new ResponseObject(error);
            responseObject.setStatus(ResponseObject.ResponseStatus.ERROR);
        } finally {
            return responseObject;
        }
    }

    @Override
    public ResponseObject findOrderByIsActive(boolean isActive) {
        ResponseObject responseObject;
        try {
            List<Order> orders = orderService.findOrderByIsActive(isActive);

            responseObject = new ResponseObject(getActiveOrdersJson(orders));
            responseObject.setStatus(ResponseObject.ResponseStatus.SUCCESS);
        } catch (Exception ex) {
            Error error = new Error("1001", ex.getMessage());
            responseObject = new ResponseObject(error);
            responseObject.setStatus(ResponseObject.ResponseStatus.ERROR);
        }
        return responseObject;
    }
    private ActiveOrderResponse getActiveOrderJson(Order order){
        return new ActiveOrderResponse(order);
    }
    private List<ActiveOrderResponse> getActiveOrdersJson(List<Order> orders){
        List<ActiveOrderResponse> orderResponses = new ArrayList<>();
        if(orders == null) return orderResponses;
        for (Order order : orders){
            orderResponses.add(getActiveOrderJson(order));
        }
        return orderResponses;
    }
}
