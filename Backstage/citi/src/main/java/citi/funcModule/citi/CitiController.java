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

    CitiAPIContext context = new CitiAPIContext();


    /**
     * 绑定花旗卡
     */
    @RequestMapping("/bindCard")
    public String bindCard(String code, String state){
        CitiCard citiCard = citiService.getCardToBeBind(code, state);
        if(citiService.binding(citiCard)){
            return "redirect:success.html";
        }
        return "redirect:../fail.html";
    }

    @ResponseBody
    @RequestMapping("/requestBind")
    public String requestBind(String userID){
        return Authorize.getURL("accounts_details_transactions cards customers_profiles","AU","GCB","en_US",userID,"http://193.112.44.141/citi/citi/bindCard");
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
        String linkCode = PayWithAwards.getLinkCode(cardNum,phoneNum,merchantCustomerReferenceId);
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

    @ResponseBody
    @RequestMapping("/citiAccount")
    public String citiAccountBind(String username, String password, String userID){
        CitiAccount accs = new CitiAccount();
        CitiAuthorize authorize = new CitiAuthorize();
        Map map = null;
        String jsCode = null;
        try{
            map = authorize.getBizToken(context);
        }catch (Exception e){
            System.out.println("error");
        }
        String accounts = null;
        String encoding = "UTF-8";
        File file = new File("./E2E.js");
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jsCode = new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
        }
        //Resource RESJS = new ClassPathResource("E:\\WannaWin\\Backstage\\citi\\src\\E2E.js");
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        String scriptResult = null;
        try{
            engine.eval(jsCode);
            Invocable invocable = (Invocable) engine;
            scriptResult = (String) invocable.invokeFunction("doRSA",map.get("modulus"),map.get("exponent"),context.getEventId(),password);
        }catch(ScriptException e){
            e.printStackTrace();
            System.out.println("Error executing script: "+ e.getMessage()+" script:["+"1"+"]");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            System.out.println("Error executing script,为找到需要的方法: "+ e.getMessage()+" script:["+"2"+"]");
        }
        try{
            accounts = accs.getAccounts(username, scriptResult,context);
        }catch (Exception e){
            System.out.println("error");
        }
        return accounts;
    }

}
