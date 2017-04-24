package creativei.service.impl;

import creativei.dao.CategoryDao;
import creativei.entity.Category;
import creativei.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryDao.findOne(id);
    }

    @Override
    public Category getByName(String name) {
        return null;
    }
}
