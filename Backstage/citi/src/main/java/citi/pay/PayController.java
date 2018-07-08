package citi.pay;
import citi.vo.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private Gson gson;
    @RequestMapping(value = "/pay/userInformation")
    @ResponseBody
    public String returnCustomerInformation(User user){
        String jsonStr = gson.toJson(user);
        return jsonStr;
    }


    @ResponseBody
    @RequestMapping(value="/pay/submitOrder",method= RequestMethod.POST)
    public String ajaxRequest(Order order, User user){
        Strategy.returnPointsTobePaid(order,user);
        user.changePoint(order);
        String jsonStr = gson.toJson(order);
        // TODO:如何再返还给用户?
        return jsonStr;
    }
}
