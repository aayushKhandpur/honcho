package creativei.manager.impl;

import creativei.entity.Restaurant;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vo.ResponseObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantManagerImpl {

    public ResponseObject getRestaurant( long id){
        //TODO unimplemented
        Restaurant restaurant = new Restaurant();

        restaurant.setName("Lazy Mojo");
        restaurant.setCity("Jaipur");
        restaurant.setAddressLine1("Malviya Nagar");
        restaurant.setAddressLine2("Jaipur");
        restaurant.setId(101L);
        ResponseObject responseObject = new ResponseObject(restaurant);


        return responseObject;
    }
    public ResponseObject getServices( long id){
        //TODO unimplemented
        List<String> services =  new ArrayList<String>();
        services.add("ORDER");
        services.add("BOOKING");
        ResponseObject responseObject = new ResponseObject(services);
        return responseObject;
    }

    public ResponseObject getTables( ){
        //TODO unimplemented
        List<String> tables =  new ArrayList<String>();
        ResponseObject responseObject = new ResponseObject(tables);
        return responseObject;
    }

}
