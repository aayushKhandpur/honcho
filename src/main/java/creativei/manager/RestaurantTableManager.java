package creativei.manager;

import org.springframework.stereotype.Service;
import vo.ResponseObject;

/**
 * Created by Administrator on 16-04-2017.
 */
public interface RestaurantTableManager {

    public ResponseObject getAll();
    public ResponseObject getById(Long id);
    public ResponseObject getByTableNumber(String name);
}
