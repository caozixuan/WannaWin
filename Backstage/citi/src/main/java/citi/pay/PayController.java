package citi.pay;
import citi.vo.Order;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

/**
 * @author zhong
 * @date 2018-7-11
 */
@RequestMapping("/pay")
@Controller
public class PayController {
    @Autowired
    private Gson gson;

    @Autowired
    private PayService payService;

    @RequestMapping("/pay")
    public void pay(String userID,String timeStamp,String merchantID,float totalPrice){


    }

    @RequestMapping("/QRCode")
    public String QRCode(String userID,String timeStamp){
        switch (payService.QRCode(userID,timeStamp)){
            case INVALID:
                return "";
            case UNUSED:
                return "";
            case USED:
                Order order=payService.getOrder(userID,timeStamp);

                return "";
            case USEFAIL:
                return "";
            default:
                return "";
        }
    }

}
