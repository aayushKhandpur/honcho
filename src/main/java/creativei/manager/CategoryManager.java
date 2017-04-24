package creativei.manager;

import creativei.entity.Category;
import vo.ResponseObject;

import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
public interface CategoryManager {
    public ResponseObject getAll();
    public ResponseObject getById(Long id);
    public ResponseObject getByName(String name);
}
