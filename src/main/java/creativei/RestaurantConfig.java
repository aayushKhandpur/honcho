package creativei;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Aayush on 9/30/2017.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RestaurantConfig {
    public static final String tableService = "RestaurantTableService";
    public String menuItemService = "MenuItemService";
    public String orderService = "OrderService";
    public String orderItemServie = "OrderItemService";

    public RestaurantConfig(){
    }
}
