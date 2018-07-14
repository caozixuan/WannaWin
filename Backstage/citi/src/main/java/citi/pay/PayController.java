package citi.pay;
import citi.resultjson.ResultJson;
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

    @RequestMapping("")
    public void pay(String userID,String timeStamp,String merchantID,float totalPrice){


    }

    @RequestMapping("/QRCode")
    public String QRCode(String userID,String timeStamp){
        switch (payService.QRCode(userID,timeStamp)){
            case INVALID:
                return ResultJson.QRCODE_INVALID;
            case UNUSED:
                return ResultJson.QRCODE_UNUSED;
            case USED:
                Order order=payService.getOrder(userID,timeStamp);
                JsonObject returnData = new JsonParser().parse(gson.toJson(order)).getAsJsonObject();
                returnData.addProperty("status","used");
                return returnData.getAsString();
            case USEFAIL:
                Order order2=payService.getOrder(userID,timeStamp);
                JsonObject returnData2 = new JsonParser().parse(gson.toJson(order2)).getAsJsonObject();
                returnData2.addProperty("status","usefail");
                return returnData2.getAsString();
            default:
                return "";
        }
    }

}
