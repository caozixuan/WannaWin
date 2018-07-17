package citi.order;

import citi.mapper.OrderMapper;
import citi.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> getOrders(String userID, String intervalTime){
        List<Order> orders = orderMapper.getOrderByUserID(userID, intervalTime);
        return orders;
    }


}
