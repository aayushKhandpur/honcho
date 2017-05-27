package vo.response;

import creativei.entity.Order;
import creativei.entity.OrderItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 18-05-2017.
 */
public class ActiveOrderResponse {
    private long id;
    private long tableId;
    private String customize;
    private double subtotal;
    private String state;
    private Date punchTime;
    private Date completionTime;
    private List<OrderItemResponse> items;

    public ActiveOrderResponse(){

    }

    public ActiveOrderResponse(Order order){
        this.id = order.getId();
        this.completionTime = order.getOrderCompletionTime();
        this.customize = order.getCustomization();
        this.punchTime = order.getOrderPunchTime();
        this.state = order.getOrderState().toString();
        this.subtotal = order.getSubtotal();
        this.tableId = order.getTable().getId();
        if(order.getOrderItemList() != null){
            if(this.items == null) this.items = new ArrayList<>();
            for (OrderItem orderItem : order.getOrderItemList()){
                this.items.add(new OrderItemResponse(orderItem));
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public String getCustomize() {
        return customize;
    }

    public void setCustomize(String customize) {
        this.customize = customize;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponse> items) {
        this.items = items;
    }

    public class OrderItemResponse{
        private long id;
        private String name;
        private long menuItemId;
        private long orderId;
        private String customize;
        private int quantity;
        private double rate;
        private double price;

        public OrderItemResponse(){

        }

        public OrderItemResponse(OrderItem orderItem){
            this.id = orderItem.getId();
            this.name = orderItem.getName();
            this.menuItemId = orderItem.getMenuItem().getId();
            this.orderId = orderItem.getOrder().getId();
            this.customize = orderItem.getCustomization();
            this.quantity = orderItem.getQuantity();
            this.rate = orderItem.getRate();
            this.price = orderItem.getPrice();
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getMenuItemId() {
            return menuItemId;
        }

        public void setMenuItemId(long menuItemId) {
            this.menuItemId = menuItemId;
        }

        public long getOrderId() {
            return orderId;
        }

        public void setOrderId(long orderId) {
            this.orderId = orderId;
        }

        public String getCustomize() {
            return customize;
        }

        public void setCustomize(String customize) {
            this.customize = customize;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
