package citi.pay;
import citi.mybatismapper.OrderMapper;
import citi.vo.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pay")
@Controller
public class PayController {
    /*
     * 作者：曹子轩
     */
    @Autowired
    private Gson gson;
    @Autowired
    private OrderMapper orderMapper;
    @RequestMapping(value = "/getUserAndOrderInformation")
    @ResponseBody
    public String returnCustomerInformation(User user, Order order){
        Strategy.returnPointsTobePaid(order,user);
        user.changePoint(order);
        String jsonStr = gson.toJson(order);
        return jsonStr;
    }

    // 这里应该通过轮询的方式请求
    @ResponseBody
    @RequestMapping(value="/submitOrder",method= RequestMethod.POST)
    public String ajaxRequest(String orderID, User user){
        Order order = orderMapper.select(orderID);
        if(order.getState()== Order.OrderState.SUCCESS||order.getState()==Order.OrderState.FAIL){
            String jsonStr = gson.toJson(order);
            return jsonStr;
        }
        return null;
    }
}
