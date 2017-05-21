package creativei.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Restaurant_Table")
public class RestaurantTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String tableNumber;

    //optional table name
    private String displayName;

    private Integer occupancy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
    }
}
