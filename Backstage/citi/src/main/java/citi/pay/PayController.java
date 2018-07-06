package citi.pay;
import net.sf.json.JSONObject;
import citi.vo.User;
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
        // TODO: 这里是不是该有一个根据token确定用户的接口?
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
    public User ajaxRequest(@RequestBody User user){
        System.out.println(user);
        return user;
    }
}
