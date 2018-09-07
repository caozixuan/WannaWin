package citi.funcModule.order;

import citi.persist.mapper.MerchantMapper;
import citi.vo.Order;

import java.sql.Timestamp;

public class ReturnOrder extends Order {
    private String merchantName;
    private String merchantLogoURL;
    public ReturnOrder(Order order, String merchantName, String merchantLogoURL) {
        super(order.getOrderId(), order.getOriginalPrice(), order.getPriceAfter(), order.getPointsNeeded(), order.getUserId(), order.getState().toString(), order.getMerchantId(), Timestamp.valueOf(order.getTime()));
        this.merchantName = merchantName;
        this.merchantLogoURL = merchantLogoURL;
    }
}
