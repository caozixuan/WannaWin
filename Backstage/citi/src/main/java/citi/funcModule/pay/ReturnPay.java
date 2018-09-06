package citi.funcModule.pay;

import citi.vo.Merchant;
import citi.vo.Order;

import java.sql.Timestamp;

/**
 * Created by zhong on 2018/7/19 11:28
 */
public class ReturnPay {
    protected String orderID;
    protected double originalPrice;
    protected double priceAfter;
    protected double pointsNeeded;
    protected String userID;
    protected String state;   //2018-07-10 从 OrderState 改为 String - 任思远
    protected String merchantID;
    protected String time;

    private String merchantName;
    private String merchantLogoURL;

    public ReturnPay(Order order, Merchant merchant){
        this.merchantName=merchant.getName();
        this.merchantLogoURL=merchant.getMerchantLogoURL();

        this.orderID = order.getOrderId();
        this.originalPrice = order.getOriginalPrice();
        this.priceAfter = order.getPriceAfter();
        this.pointsNeeded = order.getPointsNeeded();
        this.userID = order.getUserId();
        this.state = Order.OrderState.getStateString(order.getState());
        this.merchantID = order.getMerchantId();
        this.time = order.getTime();
    }
}
