package citi.funcModule.citi;

import citi.API.*;
import citi.BC.RSA;
import citi.persist.mapper.CitiMapper;
import citi.persist.mapper.TokenMapper;
import citi.persist.mapper.UserMapper;
import citi.support.resultjson.ResultJson;
import citi.vo.CitiCard;
import citi.vo.RefreshToken;
import citi.vo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.security.interfaces.RSAKey;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

/*
 * 构架：刘钟博
 * 作者：曹子轩
 */
@RequestMapping(value = {"/citi"})
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
     */
    @RequestMapping("/bindCard")
    public String bindCard(String code, String state){
        CitiCard citiCard = citiService.getCardToBeBind(code, state);
        if(citiService.binding(citiCard)){
            return "redirect:.../success.html";
        }
        return "redirect:../fail.html";
    }

    @ResponseBody
    @RequestMapping("/requestBind")
    public String requestBind(String userID){
        return Authorize.getURL("accounts_details_transactions cards customers_profiles pay_with_points","AU","GCB","en_US",userID,"http://193.112.44.141/citi/citi/bindCard");
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
        citiMapper.delete(citiCard.getCitiCardID(),citiCard.getUserID());
        return ResultJson.SUCCESS;
    }

    @RequestMapping("/refreshToekn")
    public String refreshToken(String userID){
        String formerRefreshToken = tokenMapper.select(userID);
        String[] tokens = Authorize.getTokenAndRefreshTokenByFormerRefreshToken(userID, formerRefreshToken);
        citiService.saveRefreshToken(tokens[1], userID);
        return tokens[0];
    }

    @ResponseBody
    @RequestMapping("/getInfo")
    public String getCitiCardInformation(String userID){
        CitiCard citiCard = citiMapper.getCardByID(userID);
        return gson.toJson(citiCard);
    }

    @ResponseBody
    @RequestMapping("/registerPay")
    public String registerPay(String userID, String cardNum, String phoneNum){
        String formerRefreshToken = tokenMapper.select(userID);
        String[] tokens = Authorize.getTokenAndRefreshTokenByFormerRefreshToken(userID, formerRefreshToken);
        citiService.saveRefreshToken(tokens[1], userID);
        String merchantCustomerReferenceId = UUID.randomUUID().toString();
        String linkCode = PayWithAwards.getLinkCode(cardNum,phoneNum,merchantCustomerReferenceId, Authorize.getAccessToken());
        PayWithAwards.activateCode(linkCode, tokens[0]);
        return PayWithAwards.getInformation(linkCode,tokens[0]);
    }

    @ResponseBody
    @RequestMapping("/getPointsBalance")
    public String getPointsBalance(String userID){
        String formerRefreshToken = tokenMapper.select(userID);
        String[] tokens = Authorize.getTokenAndRefreshTokenByFormerRefreshToken(userID, formerRefreshToken);
        citiService.saveRefreshToken(tokens[1], userID);
        String linkCode="";                                // 此处应当从数据库存取
        return PayWithAwards.getInformation(linkCode,tokens[0]);
    }


}
