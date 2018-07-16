package citi.pay;

import citi.mapper.OrderMapper;
import citi.mapper.StrategyMapper;
import citi.mapper.UserMapper;
import citi.vo.Order;
import citi.vo.StrategyDAO;
import citi.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    @Autowired
    private OrderMapper orderMapper;

    public boolean pay(String userID,String timeStamp,String merchantID,float totalPrice){
        long timeMillis = System.currentTimeMillis()/1000;
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
                Order order=new Order(totalPrice,strategyDAO.getPriceAfter(),strategyDAO.getPoints(),userID,"SUCCESS",merchantID,new Timestamp(QRTimestamp));
                if (orderMapper.addOrder(order)==1){
                    return true;
                }
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
        List<Order> orders=orderMapper.getOrderByUserID(userID,"10");

        for (Order order:orders){
            if (order.getTime().compareTo(new Timestamp(QRTimestamp))==0){
                if (order.getState()==Order.OrderState.FAIL){
                    return QRCodeStatus.USEFAIL;
                }
                return QRCodeStatus.USED;
            }
        }
        return QRCodeStatus.UNUSED;
    }

    public Order getOrder(String userID,String timeStamp){
        List<Order> orders=orderMapper.getOrderByUserID(userID,"0101010101");
        for (Order order :orders
                ) {
            if (order.getTime().compareTo(new Timestamp(Long.parseLong(timeStamp)))==0){
                return order;
            }
        }
        return null;
    }


}
