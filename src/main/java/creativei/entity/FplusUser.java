package creativei.entity;

import creativei.enums.UserRole;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 21-09-2016.
 */

@Entity
@Table(name = "user")
public class FplusUser extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole userRole;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public FplusUser(){}
    public FplusUser(String name) {
        this.name = name;
    }

    public FplusUser(String userId, String password, UserRole role, String name){
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.userRole = role;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "FplusUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                ", restaurant=" + restaurant +
                '}';
    }
}
