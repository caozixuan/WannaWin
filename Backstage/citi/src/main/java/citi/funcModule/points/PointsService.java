package citi.funcModule.points;

import citi.BC.BC;
import citi.BC.BC_Data;
import citi.BC.Block;
import citi.BC.DealData;
import citi.persist.mapper.MSCardMapper;
import citi.persist.mapper.MerchantMapper;
import citi.persist.mapper.UserMapper;
import citi.vo.MSCard;
import citi.vo.Merchant;
import citi.vo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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

    @Transactional
    public boolean isCanChange(List<ResultBean.MerchantBean> merchantBeanList, User user, ArrayList<ReturnMerchant> returnMerchants, ArrayList<String> ids){
        boolean isCanChange = true;
        if(merchantBeanList==null||user==null||returnMerchants==null||ids==null){
            return false;
        }
        for(int i=0;i<merchantBeanList.size();i++){
            ResultBean.MerchantBean merchantBean=  merchantBeanList.get(i);
            MSCard msCard = msCardMapper.getBy_userID_AND_merchantID(user.getUserID(), merchantBean.getMerchantID());
            Merchant merchant = merchantMapper.selectByID(merchantBean.getMerchantID());
            if(ids.indexOf(merchantBean.getMerchantID())==-1){
                ReturnMerchant returnMerchant = new ReturnMerchant(merchantBean.getMerchantID(),merchant.getName(),merchant.getCardLogoURL(), "不存在此卡");
                returnMerchants.add(returnMerchant);
                isCanChange = false;
            }
            else if(Double.valueOf(merchantBean.getSelectedMSCardPoints())<=0){
                ReturnMerchant returnMerchant = new ReturnMerchant(merchantBean.getMerchantID(),merchant.getName(),merchant.getCardLogoURL(), "兑换积分为正");
                returnMerchants.add(returnMerchant);
                isCanChange = false;
            }
            else if(msCard.getPoints()*merchant.getProportion()<Double.valueOf(merchantBean.getSelectedMSCardPoints())*merchant.getProportion()){
                ReturnMerchant returnMerchant = new ReturnMerchant(merchantBean.getMerchantID(),merchant.getName(),merchant.getCardLogoURL(), "卡内积分不足");
                returnMerchants.add(returnMerchant);
                isCanChange = false;
            }
        }
        return isCanChange;
    }

    @Transactional
    public void deductPoints(User user, List<ResultBean.MerchantBean> merchantBeanList){
        for(int i=0;i<merchantBeanList.size();i++){
            ResultBean.MerchantBean merchantBean=  merchantBeanList.get(i);
            MSCard msCard = msCardMapper.getBy_userID_AND_merchantID(user.getUserID(), merchantBean.getMerchantID());
            Merchant merchant = merchantMapper.selectByID(msCard.getMerchantId());
            if(msCard.getPoints()*merchant.getProportion()>=Double.valueOf(merchantBean.getSelectedMSCardPoints())*merchant.getProportion()){
                msCardMapper.exchangePoints(Integer.valueOf(merchantBean.getSelectedMSCardPoints()),user.getUserID(), merchantBean.getMerchantID());
                userMapper.exchangeGeneralPoints(user.getUserID(), Double.valueOf(merchantBean.getSelectedMSCardPoints())*merchant.getProportion());
                msCard.setPoints(msCard.getPoints()-Integer.valueOf(merchantBean.getSelectedMSCardPoints()));
                user.setGeneralPoints(user.getGeneralPoints()+Double.valueOf(merchantBean.getSelectedMSCardPoints())*merchant.getProportion());
                addBlock(DealData.DealType.IN,merchant.getMerchantID(),user.getUserID(),Double.valueOf(merchantBean.getSelectedMSCardPoints())*merchant.getProportion());
            }
        }
    }

    public void addBlock(DealData.DealType dealType, String merchantID, String userID, double points_citi){
        DealData data = new DealData(dealType, merchantID, userID, points_citi);
        BC.addBlock(data);
    }
    public ArrayList<ReturnInformation> dividePointsHistory(ArrayList<Points_history_merchant> points_history_merchants){
        ArrayList<ReturnInformation> returnInformations = new ArrayList<ReturnInformation>();
        ArrayList<Timestamp> timestamps = new ArrayList<Timestamp>();
        for(Points_history_merchant points_history_merchant:points_history_merchants){
            if(timestamps.indexOf(Timestamp.valueOf(points_history_merchant.getTime()))<0){
                Timestamp timestamp = Timestamp.valueOf(points_history_merchant.getTime());
                timestamps.add(timestamp);
            }
        }
        for(Timestamp timestamp:timestamps){
            returnInformations.add(new ReturnInformation());
        }
        ReturnInformation returnInformation = new ReturnInformation();
        for(Points_history_merchant points_history_merchant:points_history_merchants){
            returnInformation = returnInformations.get(timestamps.indexOf(Timestamp.valueOf(points_history_merchant.getTime())));
            returnInformation.points_history_merchants.add(points_history_merchant);
        }
        for(ReturnInformation returnIn:returnInformations){
            returnIn.setTotalPoints();
        }
        return returnInformations;
    }
}
