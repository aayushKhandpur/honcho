package creativei.manager.impl;

import creativei.dao.CategoryDao;
import creativei.entity.Category;
import creativei.manager.CategoryManager;
import creativei.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vo.ResponseObject;
import vo.modal.CategoryVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Service("CategoryManager")
public class CategoryManagerImpl implements CategoryManager{

    @Autowired
    CategoryService categoryService;


    @Override
    public ResponseObject getAll() {
        List<Category> categoryList = categoryService.getAll();
        return new ResponseObject(getCategoryVoList(categoryList), ResponseObject.ResponseStatus.SUCCESS);
    }
    private List<CategoryVo> getCategoryVoList(List<Category> categories){
        List<CategoryVo> categoryVoList = new ArrayList<>();
        if(categories == null || categories.isEmpty()) return categoryVoList;
        for(Category category : categories){
            categoryVoList.add(new CategoryVo(category));
        }
        return categoryVoList;
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
