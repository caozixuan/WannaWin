package citi.funcModule.order;

import citi.persist.mapper.MerchantMapper;
import citi.vo.Order;

import java.sql.Timestamp;

public class ReturnOrder extends Order {
    private String merchantName;
    public ReturnOrder(String orderId, double originalPrice, double priceAfter, double pointsNeeded, String userId, String state, String merchantId, Timestamp time, MerchantMapper merchantMapper) {
        super(orderId, originalPrice, priceAfter, pointsNeeded, userId, state, merchantId, time);
        this.merchantName = merchantMapper.selectByID(merchantId).getName();
    }
}
