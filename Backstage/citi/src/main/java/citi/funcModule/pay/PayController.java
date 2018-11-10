package citi.funcModule.pay;
import citi.support.resultjson.ResultJson;
import citi.vo.Merchant;
import citi.vo.Order;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhong
 * @date 2018-7-11
 */
@RequestMapping(value = {"/pay"},produces = {"text/json;charset=UTF-8"})
@Controller
public class PayController {
    @Autowired
    private Gson gson;

    @Autowired
    private PayService payService;

    @RequestMapping("")
    @ResponseBody
    public String pay(String userID,String timeStamp,String merchantID,Double totalPrice){
        if (payService.pay(userID,timeStamp,merchantID,totalPrice)){
            return ResultJson.SUCCESS;
        }else{
            return ResultJson.FAILURE;
        }
    }

    @ResponseBody
    @RequestMapping("/QRCode")
    public String QRCode(String userID,String timeStamp){
        switch (payService.QRCode(userID,timeStamp)){
            case INVALID:
                return ResultJson.QRCODE_INVALID;
            case UNUSED:
                return ResultJson.QRCODE_UNUSED;
            case USED:
                Order order=payService.getOrder(userID,timeStamp);
                Merchant merchant=payService.getMerchant(order);
                ReturnPay returnPay=new ReturnPay(order,merchant);
                return gson.toJson(returnPay);
            case USEFAIL:
                Order order2=payService.getOrder(userID,timeStamp);
                Merchant merchant2=payService.getMerchant(order2);
                ReturnPay returnPay2=new ReturnPay(order2,merchant2);
                return gson.toJson(returnPay2);
            default:
                return "0";
        }
    }

}
