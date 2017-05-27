package vo.modal;

import creativei.entity.Category;
import creativei.entity.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 01-05-2017.
 */
public class CategoryVo {
    private Long id;
    private String name;
    private Long parentId;
    private String availableFromHour;
    private String availableFromMin;
    private String availableToHour;
    private String availableToMin;
    private Boolean isAvailable;
    private int sequenceOrder;
    private List<MenuItemVo> menuItems;

    public CategoryVo(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.availableFromHour = category.getAvailableFromHour();
        this.availableFromMin = category.getAvailableFromMin();
        this.availableToHour = category.getAvailableToHour();
        this.availableToMin = category.getAvailableToMin();
        this.isAvailable = category.getAvailable();
        this.sequenceOrder = category.getSequenceOrder();
        this.menuItems = getMenuItemVoList(category.getMenuItemList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<MenuItemVo> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemVo> menuItems) {
        this.menuItems = menuItems;
    }

    private List<MenuItemVo> getMenuItemVoList(List<MenuItem> menuItems){
        if(menuItems == null) return null;
        List<MenuItemVo> menuItemVos = new ArrayList<>();
        for(MenuItem menuItem : menuItems){
            menuItemVos.add(new MenuItemVo(menuItem));
        }
        return menuItemVos;
    }
}
