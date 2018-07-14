package citi.pay;

import citi.vo.Order;
import org.springframework.stereotype.Service;

/**
 * @author zhong
 * @date 2018-7-11
 */
@Service
public class PayService {


    public boolean pay(String userID,String timeStamp,String merchantID,float totalPrice){

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
