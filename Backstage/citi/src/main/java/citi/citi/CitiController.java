package citi.citi;

import citi.API.Account;
import citi.API.Authorize;
import citi.API.Card;
import citi.vo.CitiCard;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        String accessInformation = Authorize.getAccessTokenWithGrantType(code,"https://www.baidu.com");
        String accessToken = Authorize.getToken(accessInformation);
        String acountInformation = Account.getAccountInformation(accessToken);
        String cardsInformation = Card.getCardsInformation(accessToken);
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




}
