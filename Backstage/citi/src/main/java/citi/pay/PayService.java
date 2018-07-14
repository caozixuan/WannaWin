package citi.pay;

import citi.mapper.StrategyMapper;
import citi.mapper.UserMapper;
import citi.vo.Order;
import citi.vo.StrategyDAO;
import citi.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhong
 * @date 2018-7-11
 */
@Service
public class PayService {

    @Autowired
    private StrategyMapper strategyMapper;

    @Autowired
    private UserMapper userMapper;

    public boolean pay(String userID,String timeStamp,String merchantID,float totalPrice){
        long timeMillis = System.currentTimeMillis();
        long QRTimestamp=Long.parseLong(timeStamp);
        if(timeMillis-QRTimestamp>60||timeMillis<QRTimestamp){
            return false;
        }
        List<StrategyDAO> strategyDAOList=strategyMapper.getStrategysByMerchantID(merchantID);
        Collections.sort(strategyDAOList, new Comparator<StrategyDAO>() {
            @Override
            public int compare(StrategyDAO o1, StrategyDAO o2) {
                if (o1.getPoints()>o2.getPoints()){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        User user=userMapper.getInfoByUserID(userID);
        for (StrategyDAO strategyDAO:strategyDAOList){
            if (strategyDAO.getPoints()<user.getGeneralPoints()){
                user.setGeneralPoints(user.getAvailablePoints()-strategyDAO.getPoints());
                float priceAfter=strategyDAO.getPriceAfter();
            }
        }

        return false;
    }

    public void calc(String userID,String merchantID,float totlePrice){

    }

    public QRCodeStatus QRCode(String userID,String timestamp){
        long timeMillis = System.currentTimeMillis();
        long QRTimestamp=Long.parseLong(timestamp);
        if(timeMillis-QRTimestamp>60||timeMillis<QRTimestamp){
            return QRCodeStatus.INVALID;
        }
        //TODO:从数据库搜索该ID和时间戳对应的订单
        Order order=new Order();
        if (order==null){
            return QRCodeStatus.UNUSED;
        }
        if (order.getState()==Order.OrderState.FAIL){
            return QRCodeStatus.USEFAIL;
        }
        return QRCodeStatus.USED;
    }

    public Order getOrder(String userID,String timeStamp){
        //TODO:从数据库搜索该ID和时间戳对应的订单
        return new Order();
    }


}
