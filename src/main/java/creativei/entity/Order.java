package creativei.entity;

import creativei.enums.OrderState;
import creativei.enums.SpiceIndicator;
import org.aspectj.weaver.ast.Or;
import vo.modal.OrderVo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Entity
@Table(name = "customer_order")
public class Order extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    private RestaurantTable table;
    private String customization;
    private SpiceIndicator spiceIndicator;
    @Transient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;
    private double subtotal;
    private OrderState orderState;
    private Date orderPunchTime;
    private Date orderCompletionTime;
    private boolean isActive;
    @Transient
    private List<Long> orderItemIds;
    @Transient
    private Long tableId;

    public Order() {
    }

    public Order(OrderVo orderVo) {
        this.id = orderVo.getOrderId();
        this.customization = orderVo.getcustomize();
        this.isActive = orderVo.isActive();
        this.orderState = orderVo.getOrderState();
        this.tableId = orderVo.getTableId();
        this.subtotal = orderVo.getSubtotal();
        this.spiceIndicator = orderVo.getSpiceIndicator();
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }

    public String getCustomization() {
        return customization;
    }

    public void setCustomization(String customization) {
        this.customization = customization;
    }

    public SpiceIndicator getSpiceIndicator() {
        return spiceIndicator;
    }

    public void setSpiceIndicator(SpiceIndicator spiceIndicator) {
        this.spiceIndicator = spiceIndicator;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Long> getOrderItemIds() {
        return orderItemIds;
    }

    public void setOrderItemIds(List<Long> orderItemIds) {
        this.orderItemIds = orderItemIds;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
}
