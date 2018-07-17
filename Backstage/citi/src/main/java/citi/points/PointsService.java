package citi.points;

import citi.mapper.MSCardMapper;
import citi.mapper.MerchantMapper;
import citi.mapper.UserMapper;
import citi.vo.MSCard;
import citi.vo.Merchant;
import citi.vo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointsService {
    @Autowired
    private Gson gson;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MSCardMapper msCardMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    public boolean isCanChange(List<ResultBean.MerchantBean> merchantBeanList, User user, ArrayList<ReturnMerchant> returnMerchants, ArrayList<String> ids){
        boolean isCanChange = true;
        if(merchantBeanList==null||user==null||returnMerchants==null||ids==null){
            return false;
        }
        for(int i=0;i<merchantBeanList.size();i++){
            ResultBean.MerchantBean merchantBean=  merchantBeanList.get(i);
            MSCard msCard = msCardMapper.getBy_userID_AND_merchantID(user.getUserID(), merchantBean.getMerchantID());
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
        return isCanChange;
    }

    public void deductPoints(User user, List<ResultBean.MerchantBean> merchantBeanList){
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
}
