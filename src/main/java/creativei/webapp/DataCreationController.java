package creativei.webapp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import creativei.dao.CategoryDao;
import creativei.dao.MenuItemDao;
import creativei.dao.RestaurantTableDao;
import creativei.entity.Category;
import creativei.entity.MenuItem;
import creativei.entity.RestaurantTable;
import creativei.enums.DishType;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vo.Error;
import vo.ResponseObject;
import vo.modal.CategoryVo;
import vo.modal.MenuItemVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 01-05-2017.
 */
@RestController
public class DataCreationController {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    RestaurantTableDao restaurantTableDao;
    @Autowired
    MenuItemDao menuItemDao;

    private final ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(DataCreationController.class);

    @RequestMapping(value = "/categories/create", produces = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject createCategories(HttpServletRequest request, @RequestBody String requestBody) {
        try {
            List<Category> categories = mapper.readValue(requestBody,  new TypeReference<List<Category>>(){});
            categories = categoryDao.save(categories);
            System.out.println("complete");
            return new ResponseObject(true, ResponseObject.ResponseStatus.SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("1001", e.getMessage());
            return new ResponseObject(error, ResponseObject.ResponseStatus.ERROR);
        }
    }

    @RequestMapping(value = "/menuitems/create", produces = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject createMenuItems(HttpServletRequest request, @RequestBody String requestBody) {
        List<Category> categories = categoryDao.findAll();
        Map<String, Category> categoryMap = new HashMap();
        for(Category category : categories){
            categoryMap.put(category.getName().toLowerCase(), category);
        }
        try {
            List<MenuItem> menuItems = mapper.readValue(requestBody,new TypeReference<List<MenuItem>>(){});
            for(MenuItem menuItem: menuItems){
                menuItem.setCategory(categoryMap.get(menuItem.getCategoryName()));
                menuItem.setDishType(DishType.VEG);
            }
            menuItems = menuItemDao.save(menuItems);
            return new ResponseObject("success", ResponseObject.ResponseStatus.SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseObject("error", ResponseObject.ResponseStatus.ERROR);
        }

    }

    @RequestMapping(value = "/restaurant/tables/dummy", produces = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseObject createDummyTables(HttpServletRequest request){
        List<RestaurantTable> tableList = new ArrayList();
        for(Integer i =1; i<=10 ; i++){
            RestaurantTable table = new RestaurantTable();
            table.setTableNumber(i+"");
            table.setDisplayName("Table "+i);
            tableList.add(table);
        }
        tableList = restaurantTableDao.save(tableList);
        return new ResponseObject(tableList, ResponseObject.ResponseStatus.SUCCESS);
    }

}
