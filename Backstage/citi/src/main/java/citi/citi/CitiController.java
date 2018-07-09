package citi.citi;

import citi.API.Account;
import citi.API.Authorize;
import citi.API.Card;
import citi.API.Customer;
import citi.mybatismapper.CitiMapper;
import citi.mybatismapper.TokenMapper;
import citi.mybatismapper.UserMapper;
import citi.vo.CardDetail;
import citi.vo.CitiCard;
import citi.vo.Phone;
import citi.vo.RefreshToken;
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
    private TokenMapper tokenMapper;
    @Autowired
    private CitiMapper citiMapper;
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
        String userID = "123";  //先作为测试
        String phoneNum = null;
        String creditCardNum = null;
        String accessInformation = Authorize.getAccessTokenWithGrantType(code,"https://www.baidu.com");
        String accessToken = Authorize.getToken(accessInformation);
        citiService.saveRefreshToken(accessInformation);
        phoneNum = citiService.getPhoneNum(accessToken);
        creditCardNum = citiService.getCardNum(accessToken);
        CitiCard citiCard = new CitiCard(creditCardNum,phoneNum,userID);
        if(citiService.binding(citiCard)){
            return "{status: success}";
        }
        return "{status: fail}";
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
        String refreshAccessToken = tokenMapper.select(citiCard.getUserID());
        Authorize.revokeToken(refreshAccessToken,"refresh_token");
        citiMapper.delete(citiCard.getCitiCardNum());
        return null;
    }

    @RequestMapping("/refreshToekn")
    public String refreshToken(){
        String userID = "123";
        String refreshToken = Authorize.refreshToken();
        citiService.saveRefreshToken(refreshToken);
        return null;
    }




}
