package citi.funcModule.pay;

import citi.BC.BC;
import citi.BC.DealData;
import citi.persist.mapper.MerchantMapper;
import citi.persist.mapper.OrderMapper;
import citi.persist.mapper.StrategyMapper;
import citi.persist.mapper.UserMapper;
import citi.vo.Merchant;
import citi.vo.Order;
import citi.vo.Strategy;
import citi.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static citi.vo.Order.OrderState.FAIL;

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

    @Autowired
    private MerchantMapper merchantMapper;

    @Transactional
    public boolean pay(String userID, String timeStamp, String merchantID, Double totalPrice) {
        if(totalPrice<=0)return false;
        long timeMillis = System.currentTimeMillis() / 1000;
        long QRTimestamp = Long.parseLong(timeStamp);
        if (timeMillis - QRTimestamp > 60 || timeMillis < QRTimestamp) {
            return false;
        }
        List<Strategy> strategyList = strategyMapper.getStrategysByMerchantID(merchantID);
        Collections.sort(strategyList, new Comparator<Strategy>() {
            @Override
            public int compare(Strategy o1, Strategy o2) {
                if (o1.getPoints() > o2.getPoints()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        User user = userMapper.getInfoByUserID(userID);
        for (Strategy strategy : strategyList) {
            if (strategy.getPoints() < user.getGeneralPoints() && strategy.getFull() <= totalPrice) {
                userMapper.useGeneralPoints(userID, strategy.getPoints());
                Double priceAfter = strategy.getPriceAfter();
                Order order = new Order(totalPrice, priceAfter, strategy.getPoints(), userID, Order.OrderState.SUCCESS, merchantID, new Timestamp(QRTimestamp * 1000));
                if (orderMapper.addOrder(order) == 1) {
                    addBlock(DealData.DealType.OUT,merchantID,userID,strategy.getPoints());
                    return true;
                }
            }
        }
        Order order = new Order(totalPrice, totalPrice, 0.0, userID, Order.OrderState.FAIL, merchantID, new Timestamp(QRTimestamp * 1000));
        if (orderMapper.addOrder(order) == 1) {
            return true;
        }
        return false;
    }


    public void addBlock(DealData.DealType dealType, String merchantID, String userID, double points_citi){
        DealData data = new DealData(dealType, merchantID, userID, points_citi);
        BC.addBlock(data);
    }


    public void calc(String userID, String merchantID, float totlePrice) {

    }

    @Transactional
    public QRCodeStatus QRCode(String userID, String timestamp) {
        long timeMillis = System.currentTimeMillis() / 1000;
        long QRTimestamp = Long.parseLong(timestamp);
        if (timeMillis - QRTimestamp > 60 || timeMillis < QRTimestamp) {
            return QRCodeStatus.INVALID;
        }
        List<Order> orders = orderMapper.getOrderByUserID(userID, "101");

        for (Order order : orders) {
            Timestamp oderTimestamp = Timestamp.valueOf(order.getTime());
            if (oderTimestamp.compareTo(new Timestamp(QRTimestamp * 1000)) == 0) {
                if (order.getState() == FAIL) {
                    return QRCodeStatus.USEFAIL;
                }
                return QRCodeStatus.USED;
            }
        }
        return QRCodeStatus.UNUSED;
    }

    public Order getOrder(String userID, String timeStamp) {
        List<Order> orders = orderMapper.getOrderByUserID(userID, "0101010101");
        for (Order order : orders
                ) {
            Timestamp timestamp = Timestamp.valueOf(order.getTime());
            if (timestamp.compareTo(new Timestamp(Long.parseLong(timeStamp) * 1000)) == 0) {
                return order;
            }
        }
        return null;
    }

    public Merchant getMerchant(Order order) {
        return merchantMapper.selectByID(order.getMerchantId());
    }


}
