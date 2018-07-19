package citi.order;

import citi.mapper.MerchantMapper;
import citi.mapper.OrderMapper;
import citi.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    MerchantMapper merchantMapper;
    public List<Order> getOrders(String userID, String intervalTime){
        List<Order> orders = orderMapper.getOrderByUserID(userID, intervalTime);
        return orders;
    }
    //String orderId, double originalPrice, double priceAfter, double pointsNeeded, String userId, String state, String merchantId, Timestamp time, MerchantMapper merchantMapper
    public ArrayList<ReturnOrder> changeToReturnOrders(List<Order> orders){
        ArrayList<ReturnOrder> returnOrders = new ArrayList<ReturnOrder>();
        for(int i=0;i<orders.size();i++){
            Order order = orders.get(i);
            returnOrders.add(new ReturnOrder(order.getOrderId(),order.getOriginalPrice(),order.getPriceAfter(),order.getPointsNeeded(),order.getUserId(),order.getState().toString(),order.getMerchantId(),order.getTime(),merchantMapper));
        }
        return returnOrders;
    }


}
