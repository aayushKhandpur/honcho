package vo.modal;

import creativei.entity.MenuItem;
import creativei.entity.Order;
import creativei.enums.SpiceIndicator;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 09-05-2017.
 */
public class OrderItemVo {
    private Long id;
    private Long menuItemId;
    private String customization;
    private SpiceIndicator spiceIndicator = SpiceIndicator.STANDARD;
    private int quantity;
    private double rate;
    private String name;
    private double price;
    private boolean isVeg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public boolean isVeg() {
        return isVeg;
    }

    public void setVeg(boolean veg) {
        isVeg = veg;
    }
}
