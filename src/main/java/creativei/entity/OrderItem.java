package creativei.entity;

import creativei.enums.SpiceIndicator;
import vo.modal.OrderItemVo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 26-03-2017.
 */
@Entity
public class OrderItem extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    private String customization;

    private SpiceIndicator spiceIndicator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private int quantity;

    private double rate;

    private String name;

    private double price;

    public OrderItem() {
    }

    public OrderItem(OrderItemVo orderItemVo) {
        this.name = orderItemVo.getName();
        this.id = orderItemVo.getId();
        this.spiceIndicator = orderItemVo.getSpiceIndicator();
        this.customization = orderItemVo.getCustomization();
        this.quantity = orderItemVo.getQuantity();
        this.rate = orderItemVo.getRate();
        this.price = orderItemVo.getPrice();
        this.menuItem = new MenuItem();
        this.menuItem.setId(orderItemVo.getMenuItemId());
    }


    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
