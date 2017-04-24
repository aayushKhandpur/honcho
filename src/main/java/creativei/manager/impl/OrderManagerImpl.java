package creativei.manager.impl;

import creativei.entity.Order;
import org.springframework.stereotype.Service;
import vo.ResponseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 26-03-2017.
 */
@Service
public class OrderManagerImpl {

    public ResponseObject getAllByState(String state){
        List<Order> resultList= new ArrayList<Order>();
        ResponseObject responseObject = new ResponseObject(resultList);
        return responseObject;
    }

    public ResponseObject getById(Long id){
        Order order =  new Order();
        ResponseObject responseObject = new ResponseObject(order);
        Exception e = new UnsupportedOperationException("Not supported");
        responseObject = new ResponseObject(e.getMessage(), "1001");
        responseObject.setStatus(ResponseObject.ResponseStatus.ERROR);
        return responseObject;
    }
}
