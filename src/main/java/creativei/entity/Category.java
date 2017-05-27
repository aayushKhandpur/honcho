package creativei.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Entity
public class Category extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long parentId;
    private String availableFromHour;
    private String availableFromMin;
    private String availableToHour;
    private String availableToMin;
    private Boolean isAvailable = true;
    private int sequenceOrder = 100;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", targetEntity = MenuItem.class)
    private List<MenuItem> menuItemList;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAvailableFromHour() {
        return availableFromHour;
    }

    public void setAvailableFromHour(String availableFromHour) {
        this.availableFromHour = availableFromHour;
    }

    public String getAvailableFromMin() {
        return availableFromMin;
    }

    public void setAvailableFromMin(String availableFromMin) {
        this.availableFromMin = availableFromMin;
    }

    public String getAvailableToHour() {
        return availableToHour;
    }

    public void setAvailableToHour(String availableToHour) {
        this.availableToHour = availableToHour;
    }

    public String getAvailableToMin() {
        return availableToMin;
    }

    public void setAvailableToMin(String availableToMin) {
        this.availableToMin = availableToMin;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public int getSequenceOrder() {
        return sequenceOrder;
    }

    public void setSequenceOrder(int sequenceOrder) {
        this.sequenceOrder = sequenceOrder;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
