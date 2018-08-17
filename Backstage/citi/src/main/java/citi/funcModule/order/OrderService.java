package citi.funcModule.order;

import citi.persist.mapper.MerchantMapper;
import citi.persist.mapper.OrderMapper;
import citi.vo.Merchant;
import citi.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ArrayList<ReturnOrder> changeToReturnOrders(List<Order> orders){
        ArrayList<ReturnOrder> returnOrders = new ArrayList<ReturnOrder>();
        for(int i=0;i<orders.size();i++){
            Order order = orders.get(i);
            if(order.getState()==null)
                order.setState(Order.OrderState.FAIL.toString());
            Merchant merchant = merchantMapper.selectByID(order.getMerchantId());
            returnOrders.add(new ReturnOrder(order,merchant.getName(),merchant.getMerchantLogoURL()));
        }
        return returnOrders;
    }


}
