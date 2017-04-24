package creativei.entity;

import creativei.enums.DishType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 26-03-2017.
 */
@Entity
public class MenuItem extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName="ID", nullable = false)
    private Category category;
    private DishType dishType;
    private String description;
    private Boolean isComboMeal = false;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
