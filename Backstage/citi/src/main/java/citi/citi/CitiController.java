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

    String userID;  // 测试用

    /**
     * 绑定花旗卡
     * @param code
     * @return 成功：{"isBinding":true}，失败：{"isBinding":false}
     */
    @ResponseBody
    @RequestMapping("/bindCard")
    public String bindCard(String code){
        String phoneNum = null;
        String creditCardNum = null;
        String accessInformation = Authorize.getAccessTokenWithGrantType(code,"http://193.112.44.141/citi/bindCard");
        String accessToken = Authorize.getToken(accessInformation);
        citiService.saveRefreshToken(accessInformation, userID);
        phoneNum = citiService.getPhoneNum(accessToken);
        creditCardNum = citiService.getCardNum(accessToken);
        CitiCard citiCard = new CitiCard(creditCardNum,phoneNum,userID);
        if(citiService.binding(citiCard)){
            return "{status: success"+phoneNum+creditCardNum+"}";
        }
        return "{status: fail}";
    }

    @ResponseBody
    @RequestMapping("/requestBind")
    public String requestBind(String userID){
        this.userID = userID;
        return Authorize.getURL("accounts_details_transactions cards customers_profiles","AU","GCB","en_US","123456","http://193.112.44.141/citi/bindCard");
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
    public String refreshToken(String userID){
        String refreshToken = Authorize.refreshToken();
        citiService.saveRefreshToken(refreshToken, userID);
        return "{status:success}";
    }

}
