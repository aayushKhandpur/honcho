package creativei.webapp;

import creativei.manager.CategoryManager;
import creativei.manager.impl.OrderManagerImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vo.ResponseObject;

@RestController
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryManager categoryManager;

    @RequestMapping(value = "/categories", produces = "application/json", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseObject categories(HttpServletRequest request) {
        logger.info("Request to get categories");
        return categoryManager.getAll();
    }
}
