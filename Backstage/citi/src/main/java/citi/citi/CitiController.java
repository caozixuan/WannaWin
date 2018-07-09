package citi.citi;

import citi.API.Account;
import citi.API.Authorize;
import citi.API.Card;
import citi.API.Customer;
import citi.vo.CardDetail;
import citi.vo.CitiCard;
import citi.vo.Phone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@RequestMapping("/citi")
@Controller
public class CitiController {

    @Autowired
    private CitiService citiService;

    @Autowired
    private Gson gson;

    /**
     * 绑定花旗卡
     * @param code
     * @return 成功：{"isBinding":true}，失败：{"isBinding":false}
     */
    @ResponseBody
    @RequestMapping("/bindCard")
    public String bindCard(String code){
        // TODO:怎么获取当前请求的用户？
        String phoneNum;
        String creditCardNum;
        String accessInformation = Authorize.getAccessTokenWithGrantType(code,"https://www.baidu.com");
        String accessToken = Authorize.getToken(accessInformation);
        String refreshAccessToken = Authorize.getRefreshToken(accessInformation);
        /*
         TODO:在这里应该将accessToken存入数据库
         */
        String acountInformation = Account.getAccountInformation(accessToken);
        String cardsInformation = Card.getCardsInformation(accessToken);
        String phoneInformation = Customer.getCustomerPhone(accessToken);
        ArrayList<Phone> phones = gson.fromJson(phoneInformation, new TypeToken<ArrayList<Phone>>() {
        }.getType());
        if(phones.size()==1){
            phoneNum = String.valueOf(phones.get(0).getPhoneNum());
        }
        ArrayList<CardDetail> cardDetails = gson.fromJson(cardsInformation, new TypeToken<ArrayList<CardDetail>>() {
        }.getType());
        if(cardDetails.size()==1){
            creditCardNum = cardDetails.get(0).getDisplayCardNumber();
        }
        /*
         TODO:拿到手机号和卡号，下面开始数据库的操作，数据库表的设计有问题
         */


        /*
          TODO:后面欠json解析和把相关数据存入数据库
          某一个表中是不是要增加refresh_token?
         */
        return "{state:success}";
    }

    @ResponseBody
    @RequestMapping("/requestBind")
    public String requestBind(){
        return Authorize.getURL("accounts_details_transactions cards customers_profiles","AU","GCB","en_US","123456","https://www.baidu.com");
    }

    /**
     * @param citiCard 将被解绑的卡
     * @return 成功：{"unBinding":true}，失败：{"unBinding":false}
     */
    @RequestMapping("/unbind")
    public String unBind(CitiCard citiCard){


        return null;
    }

    @RequestMapping("/refreshToekn")
    public String refreshToken(){
        // 这里同样涉及数据库操作
        String refreshToken = Authorize.refreshToken();
        // 把得到的token再次存入数据库
        // 根据数据库操作结果返回值
        return null;
    }




}
