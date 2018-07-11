package citi.citi;

import citi.API.Authorize;
import citi.mapper.CitiMapper;
import citi.mapper.TokenMapper;
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
        CitiCard citiCard = citiService.getCardToBeBind(code);
        if(citiService.binding(citiCard)){
            return gson.toJson(citiCard);
        }
        return "{status: fail}";
    }

    @ResponseBody
    @RequestMapping("/requestBind")
    public String requestBind(String userID){
        citiService.userID = userID;
        return Authorize.getURL("accounts_details_transactions cards customers_profiles","AU","GCB","en_US","123456","http://193.112.44.141/citi/citi/bindCard");
    }

    /**
     * @param citiCard 将被解绑的卡
     * @return 成功：{"unBinding":true}，失败：{"unBinding":false}
     */
    @ResponseBody
    @RequestMapping("/unbind")
    public String unBind(CitiCard citiCard){
        String refreshAccessToken = tokenMapper.select(citiCard.getUserID());
        Authorize.revokeToken(refreshAccessToken,"refresh_token");
        citiMapper.delete(citiCard.getCitiCardNum());
        return "{status: success}";
    }

    @RequestMapping("/refreshToekn")
    public String refreshToken(String userID){
        String formerRefreshToken = tokenMapper.select(userID);
        String[] tokens = Authorize.getTokenAndRefreshTokenByFormerRefreshToken(userID, formerRefreshToken);
        citiService.saveRefreshToken(tokens[1], userID);
        return tokens[0];
    }

}
