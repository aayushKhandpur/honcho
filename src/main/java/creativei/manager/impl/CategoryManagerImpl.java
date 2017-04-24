package creativei.manager.impl;

import creativei.dao.CategoryDao;
import creativei.entity.Category;
import creativei.manager.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vo.ResponseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Service("CategoryManager")
public class CategoryManagerImpl implements CategoryManager{

    @Autowired
    CategoryDao categoryDao;

    @Override
    public ResponseObject getAll() {
        List<Category> categoryList = new ArrayList<Category>();
        return new ResponseObject(categoryList);
    }

    @Override
    public ResponseObject getById(Long id) {
        return null;
    }

    @Override
    public ResponseObject getByName(String name) {
        return null;
    }
}
