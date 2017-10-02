package creativei.webapp;

import creativei.RestaurantConfig;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class FPlusController {

    @Autowired
    RestaurantConfig restaurantConfig;

    public final String RESTAURANT_CONFIG = "restaurantConfig";

  //  @RequestMapping(value = {"/", "/restaurant"}, produces = "application/json", method = RequestMethod.GET)
    //@ResponseBody
    public Object saasBase(HttpServletRequest request, HttpSession session){

        session.setAttribute(RESTAURANT_CONFIG, restaurantConfig);
        return "config set";
    }

}
