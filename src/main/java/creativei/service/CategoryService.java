package creativei.service;

import creativei.entity.Category;

import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
public interface CategoryService {
    public List<Category> getAll();
    public Category getById(Long id);
    public Category getByName(String name);
}
