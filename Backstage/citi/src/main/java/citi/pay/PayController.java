package citi.pay;
import citi.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static citi.vo.User.getUserByToken;

@Controller
public class PayController {
    /*
     * 作者：曹子轩
     */
    @RequestMapping(value = "/pay/{token}")
    @ResponseBody
    public Map<String, Object> returnCustomerInformation(@PathVariable String token){
        // TODO: 这里是不是该有一个根据token确定用户的接口?或者传过来的是用户信息？
        User user = getUserByToken(token);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("ID",user.getUserID());
        map.put("phoneNum",user.getPhoneNum());
        map.put("generalPoints",user.getGeneralPoints());
        map.put("availablePoints",user.getAvailablePoints());
        map.put("citiCardNum",user.getCitiCardNum());
        return map;
    }


    @ResponseBody
    @RequestMapping(value="/pay/submitOrder",method= RequestMethod.POST)
    public Map<String, Object> ajaxRequest(@RequestBody Order order){
        User user = new User("13013550115");  //这里怎么定义接口获取请求商户？
        int points = Strategy.returnPointsTobePaid(order,user);
        user.changePoint(order);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("orderId",order.getOrderId());
        map.put("originalPrice",order.getOriginalPrice());
        map.put("priceAfter",order.getPriceAfter());
        map.put("pointsNeeded",order.getPointsNeeded());
        map.put("state",order.getState());
        // TODO:同时也应该给用户返回
        return map;
    }
}
