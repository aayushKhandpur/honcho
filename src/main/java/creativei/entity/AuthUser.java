package creativei.entity;

import creativei.RestaurantConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by Aayush on 10/1/2017.
 */
public class AuthUser extends User {

    RestaurantConfig restaurantConfig;
    Restaurant restaurant;
    public AuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Restaurant restaurant) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        restaurantConfig =  new RestaurantConfig();
        this.restaurant = restaurant;
    }

    public AuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        restaurantConfig =  new RestaurantConfig();
    }
}
