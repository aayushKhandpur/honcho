package creativei.service.impl;

import creativei.dao.OrderDao;
import creativei.entity.Order;
import creativei.entity.OrderItem;
import creativei.entity.RestaurantTable;
import creativei.enums.OrderState;
import creativei.service.OrderService;
import creativei.service.RestaurantTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    OrderDao orderDao;

    @Autowired
    RestaurantTableService restaurantTableService;
    @Override
    public List<Order> findOrderByState(OrderState orderState) {
        if (orderState != null) {
            return orderDao.findByOrderState(orderState);
        } else {
            logger.error("orderState is null");
        }
        return new ArrayList<>();
    }

    @Override
    public Order findOrder(Long id) {
        return orderDao.findOne(id);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderDao.save(order);
    }

    @Override
    public Order createOrder(Order order) throws Exception {
        updateOrderAndAddTableByTableId(order);
        return orderDao.save(order);
    }

    @Override
    public List<Order> findOrderByIsActive(boolean isActive) {
        return orderDao.findByIsActive(isActive);
    }

    private void updateOrderAndAddOrderItemsByOrderItemIds(Order order) {
        if (order.getOrderItemIds() != null && !order.getOrderItemIds().isEmpty()) {
            List<OrderItem> items = new ArrayList<>();
            for (Long orderItemId : order.getOrderItemIds()) {
                OrderItem item = entityManager.getReference(OrderItem.class, orderItemId);
                item.setOrder(order);
                items.add(item);
            }
            order.setOrderItemList(items);
        }
    }

    private void updateOrderAndAddTableByTableId(Order order) throws Exception {
        if (order.getTableId() != null) {
            RestaurantTable table = restaurantTableService.getById(order.getTableId());
            if(table == null){
                logger.error("No table found with table Id: "+ order.getTableId());
                throw new Exception("No Table found with table Id.");
            }
            table.setId(order.getTableId());
            order.setTable(table);
        }
    }

    public void addOrderToOrderItems(List<OrderItem> orderItems, Order order){
        if(orderItems!=null && !orderItems.isEmpty()){
            for (OrderItem item : orderItems){
                item.setOrder(order);
            }
        }
    }
}
