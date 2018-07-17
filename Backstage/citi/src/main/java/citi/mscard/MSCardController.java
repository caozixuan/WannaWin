package citi.mscard;

import citi.vo.MSCard;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/*
 * 接口设计：刘钟博
 * 代码填充：曹子轩
 */
//Membership card
@Controller
@RequestMapping("/mscard")
public class MSCardController {

    @Autowired
    private Gson gson;
    @Autowired
    private MSCardService msCardService;

    /**
     * 登陆成功时请求
     * @url /mscard/infos
     * @param userID 用户id
     * @param n 请求积分最多的n张卡
     * @return [{"cardID":"a7a8f255-f129-4b46-9de2-55f07c7ff65e","userID":"1ac9be07-f446-458c-b325-df2c7ecd113f","card_No":"888888","points":0}]
     */
    @ResponseBody
    @RequestMapping("/infos")
    public String getMSInfo(String userID,int n){
        List<MSCard> cards = msCardService.getInfo(userID, n);
        if(cards==null){
            return "[]";
        }
        ArrayList<BriefCard> briefCards = msCardService.changeToBriefCards(cards);
        if(briefCards==null)
            return "[]";
        String jsonStr = gson.toJson(briefCards);
        return jsonStr;
    }

    @ResponseBody
    @RequestMapping("/getNum")
    public String getCardNum(String userID){
        int num = msCardService.getCardNum(userID);
        return "{\"num\": "+ num + "}";
    }

    @ResponseBody
    @RequestMapping("/getDetailCard")
    public String getDetailCard(String userID, String merchantID){
        DetailCard detailCard = msCardService.getDetailCard(userID,merchantID);

        return gson.toJson(detailCard);
    }



    /**
     *@param userID 会员卡
     * @return 成功：{"state":true}，失败：{"state":false}
     */
    @ResponseBody
    @RequestMapping("/addcard")
    public String addMSCard(String userID, String merchantID, String cardNum, String password){
        boolean flag = msCardService.addMSCard(userID,merchantID,cardNum,password);
        return "{state: "+flag+"}";
    }
}
