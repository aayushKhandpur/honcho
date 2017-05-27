package vo.modal;

import creativei.entity.Category;
import creativei.entity.MenuItem;
import creativei.enums.DishType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 01-05-2017.
 */
public class MenuItemVo {
    private Long id;
    private String name;
    private double price;
    private Category category;
    private Long categoryId;
    private DishType dishType;
    private String description;
    private Boolean isComboMeal = false;
    private int quantity = 0;

    public MenuItemVo(MenuItem menuItem){
        this.categoryId = menuItem.getCategory().getId();
        this.description = menuItem.getDescription();
        this.dishType = menuItem.getDishType();
        this.id = menuItem.getId();
        this.isComboMeal = menuItem.getComboMeal();
        this.name = menuItem.getName();
        this.price = menuItem.getPrice();
    }

    public List<MenuItemVo> getMenuItemVoList(List<MenuItem> menuItems){
        if(menuItems == null) return null;
        List<MenuItemVo> menuItemVos = new ArrayList<>();
        for(MenuItem menuItem : menuItems){
            menuItemVos.add(new MenuItemVo(menuItem));
        }
        return menuItemVos;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getComboMeal() {
        return isComboMeal;
    }

    public void setComboMeal(Boolean comboMeal) {
        isComboMeal = comboMeal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
