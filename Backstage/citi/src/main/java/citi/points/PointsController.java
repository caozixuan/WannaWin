package citi.points;

import citi.mapper.MSCardMapper;
import citi.mapper.MerchantMapper;
import citi.mapper.UserMapper;
import citi.vo.MSCard;
import citi.vo.Merchant;
import citi.vo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/7/14 10:26
 */
@RequestMapping("/points")
@Controller
public class PointsController {
    @Autowired
    private Gson gson;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MSCardMapper msCardMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @ResponseBody
    @RequestMapping("/changePoints")
    public String changePoints(String json){
        //GSON直接解析成对象
        ResultBean resultBean = new Gson().fromJson(json,ResultBean.class);
        //对象中拿到集合
        List<ResultBean.MerchantBean> merchantBeanList = resultBean.getMerchants();
        User user = userMapper.getInfoByUserID(resultBean.getUserID());
        List<MSCard> msCards = msCardMapper.select(user.getUserID());
        ArrayList<String> ids = new ArrayList<String>();
        ArrayList<ReturnMerchant> returnMerchants = new ArrayList<ReturnMerchant>();
        for(int i=0;i<msCards.size();i++){
           ids.add(msCards.get(i).getMerchantId());
        }
        boolean isCanChange = true;
        for(int i=0;i<merchantBeanList.size();i++){
            ResultBean.MerchantBean merchantBean=  merchantBeanList.get(i);
            List<MSCard> userMSCards = msCardMapper.select(user.getUserID());
            MSCard msCard = userMSCards.get(0);
            if(ids.indexOf(merchantBean.getMerchantID())==-1){
                Merchant merchant = merchantMapper.selectByID(merchantBean.getMerchantID());
                ReturnMerchant returnMerchant = new ReturnMerchant(merchantBean.getMerchantID(),merchant.getName(),merchant.getCardLogoURL(), "不存在此卡");
                returnMerchants.add(returnMerchant);
                isCanChange = false;
            }
            else if(msCard.getPoints()*msCard.getProportion()<Double.valueOf(merchantBean.getSelectedMSCardPoints())*msCard.getProportion()){
                Merchant merchant = merchantMapper.selectByID(merchantBean.getMerchantID());
                ReturnMerchant returnMerchant = new ReturnMerchant(merchantBean.getMerchantID(),merchant.getName(),merchant.getCardLogoURL(), "卡内积分不足");
                returnMerchants.add(returnMerchant);
                isCanChange = false;
            }
        }
        if(isCanChange){
            List<MSCard> userMSCards = msCardMapper.select(user.getUserID());
            MSCard msCard = userMSCards.get(0);
            for(int i=0;i<merchantBeanList.size();i++){
                ResultBean.MerchantBean merchantBean=  merchantBeanList.get(i);
                if(msCard.getPoints()*msCard.getProportion()<Double.valueOf(merchantBean.getSelectedMSCardPoints())*msCard.getProportion()){
                    msCard.setPoints(msCard.getPoints()-Integer.valueOf(merchantBean.getSelectedMSCardPoints()));
                    user.setGeneralPoints(user.getGeneralPoints()+Double.valueOf(merchantBean.getSelectedMSCardPoints())*msCard.getProportion());
                }
            }
        }
        return gson.toJson(returnMerchants);
    }
}
