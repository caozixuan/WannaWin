package citi.mscard;

import citi.dao.MSCardDAO;
import citi.vo.MSCard;
import citi.vo.MSCardType;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param userId 用户id
     * @param n 请求积分最多的n张卡
     * @return
     */
    @ResponseBody
    @RequestMapping("/infos")
    public String getMSInfo(String userId,int n){
        List<MSCard> cards = msCardService.getInfo(userId, n);
        if(cards==null){
            return "{result:null}";
        }
        String jsonStr = gson.toJson(cards);
        return jsonStr;
    }

    /**
     * 一个商户可能有多张卡，返回该商户所有卡的类型
     * @param merchantID
     * @return [{"MerchantID":"xxxx","Mtype":"xxxx","cardType":"xxxx"},{},{}...]
     */
    @ResponseBody
    @RequestMapping("/cardtype")
    public String getCardType(String merchantID){
        List<MSCardType> msCardTypes = msCardService.getTypes(merchantID);
        String jsonStr = gson.toJson(msCardTypes);
        return jsonStr;
    }

    /**
     *@param msCard 会员卡
     * @return 成功：{"isBinding":true}，失败：{"isBinding":false}
     */
    @ResponseBody
    @RequestMapping("/addcard")
    public String addMSCard(MSCardDAO msCard){
        boolean flag = msCardService.addMSCard(msCard);
        return "{state: "+flag+"}";
    }
}
