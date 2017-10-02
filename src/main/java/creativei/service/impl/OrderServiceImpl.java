package creativei.service.impl;

import creativei.RestaurantConfig;
import creativei.dao.OrderDao;
import creativei.entity.Order;
import creativei.entity.OrderItem;
import creativei.entity.RestaurantTable;
import creativei.enums.OrderItemState;
import creativei.enums.OrderState;
import creativei.service.OrderItemService;
import creativei.service.OrderService;
import creativei.service.RestaurantTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
    @Lazy
    @Resource(name = RestaurantConfig.tableService)
    RestaurantTableService restaurantTableService;

    @Autowired
    OrderItemService orderItemService;

    private static ConcurrentHashMap<Long, Order> runningOrderCache = new ConcurrentHashMap<>();

    public ConcurrentHashMap<Long, Order> getRunningOrderCache() {
        return runningOrderCache;
    }

    private Order getRunningOrderFromCache(Long id){
        return runningOrderCache.getOrDefault(id, null);
    }

    private void syncOrderCache(Order order){
        if(order.getId() == null){
            logger.error("Could not sync order with id null" + order.toString());
            return;
        }
        if(order.getOrderState()== OrderState.CANCEL){
            runningOrderCache.remove(order.getId());
            return;
        }
        if(order.getOrderItemList().isEmpty()){

            logger.error("Could not sync order with no order item.");
            return;
        }
        runningOrderCache.put(order.getId(), order);
    }

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
        Order order = getRunningOrderFromCache(id);

        if(order != null){
            return order;
        }else{
            order = orderDao.findOne(id);
            syncOrderCache(order);
            return order;
        }
    }

    @Override
    public Order updateOrder(Order order) throws Exception{
        Order currentOrder = findOrder(order.getId());
        if(currentOrder != null && !currentOrder.getOrderItemList().isEmpty()){
            List<OrderItem> removeItems = new ArrayList<>();
            removeItems.addAll(currentOrder.getOrderItemList());
            removeItems.removeAll(order.getOrderItemList());
            if(removeItems.size()>0){
                removeItems.forEach(orderItem -> {
                    orderItem.setItemState(OrderItemState.CANCEL);
                });
                orderItemService.updateAll(removeItems);
            }
        }
        updateOrderAndAddTableByTableId(order);
        if(order.getOrderItemList().size() == 0){
            order.setOrderState(OrderState.CANCEL);
            order.setActive(false);
        }
        Order updatedorder = orderDao.save(order);
        updatedorder.setOrderItemList(updatedorder.getOrderItemList().stream().filter(item ->
            item.getItemState() != OrderItemState.CANCEL
        ).collect(Collectors.toList()));
        syncOrderCache(order);
        return order;
    }

    @Override
    public Order createOrder(Order order) throws Exception {
        updateOrderAndAddTableByTableId(order);
        order.setOrderState(OrderState.INITIATE);
        order.getOrderItemList().forEach(orderItem -> orderItem.setItemState(OrderItemState.PREPARING));
        Order createdOrder = orderDao.save(order);
        syncOrderCache(createdOrder);
        return order;
    }

    @Override
    public List<Order> findOrderByIsActive(boolean isActive) {
        Collection<Order> orders = getRunningOrderCache().values();
        if(!orders.isEmpty()){
            return orders.stream().filter(order -> order.isActive()).collect(Collectors.toList());
        }else{
            List<Order> orderList = orderDao.findByIsActive(isActive);
            orderList.forEach(order -> syncOrderCache(order));
            return orderList;
        }

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
