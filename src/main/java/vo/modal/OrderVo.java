package vo.modal;

import creativei.entity.OrderItem;
import creativei.enums.OrderState;
import creativei.enums.SpiceIndicator;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 09-05-2017.
 */
public class OrderVo {

    private Long orderId;
    private Long tableId;
    private String customize;
    private SpiceIndicator spiceIndicator;
    private List<OrderItemVo> items;
    private double subtotal;
    private OrderState orderState = OrderState.PLACED;
    private Date orderPunchTime;
    private Date orderCompletionTime;
    private boolean isActive = true;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getcustomize() {
        return customize;
    }

    public void setcustomize(String customization) {
        this.customize = customization;
    }

    public SpiceIndicator getSpiceIndicator() {
        return spiceIndicator;
    }

    public void setSpiceIndicator(SpiceIndicator spiceIndicator) {
        this.spiceIndicator = spiceIndicator;
    }

    public List<OrderItemVo> getItems() {
        return items;
    }

    public void setItems(List<OrderItemVo> items) {
        this.items = items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Date getOrderPunchTime() {
        return orderPunchTime;
    }

    public void setOrderPunchTime(Date orderPunchTime) {
        this.orderPunchTime = orderPunchTime;
    }

    public Date getOrderCompletionTime() {
        return orderCompletionTime;
    }

    public void setOrderCompletionTime(Date orderCompletionTime) {
        this.orderCompletionTime = orderCompletionTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getCustomize() {
        return customize;
    }

    public void setCustomize(String customize) {
        this.customize = customize;
    }
}
