package citi.vo;

/*
 * 作者：曹子轩
 * 创建订单实体类
 */

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    protected String orderID;
    protected Double originalPrice;
    protected Double priceAfter;
    protected Double pointsNeeded;
    protected String userID;
    protected String state;   //2018-07-10 从 OrderState 改为 String - 任思远
    protected String merchantID;
    protected String time;

    public enum OrderState {
        SUCCESS, FAIL, TOBEFINISHED;

        static Map<String, OrderState> enumMap1 = new HashMap<>();
        static Map<OrderState, String> enumMap2 = new HashMap<>();

        static {
            enumMap1.put("SUCCESS", SUCCESS);
            enumMap1.put("FAIL", FAIL);
            enumMap1.put("TOBEFINISHED", TOBEFINISHED);

            enumMap2.put(SUCCESS, "SUCCESS");
            enumMap2.put(FAIL, "FAIL");
            enumMap2.put(TOBEFINISHED, "TOBEFINISHED");
        }

        public static OrderState getOrderState(String orderState) {
            return enumMap1.get(orderState);
        }

        public static String getStateString(OrderState orderState) {
            return enumMap2.get(orderState);
        }

    }

    public Order() {

    }

    public Order(String orderId, Double originalPrice, Double priceAfter, Double pointsNeeded, String userId, String state, String merchantId, Timestamp time) {
        System.out.println(time.toString());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String timeString = formatter.format(time);
        this.orderID = orderId;
        this.originalPrice = Double.parseDouble(String.format("%.2f", originalPrice));
        this.priceAfter = Double.parseDouble(String.format("%.2f", priceAfter));
        this.pointsNeeded = Double.parseDouble(String.format("%.2f", pointsNeeded));
        this.userID = userId;
        this.state = state;
        this.merchantID = merchantId;
        this.time = timeString;
    }

    public Order(Double originalPrice, Double priceAfter, Double pointsNeeded, String userId, OrderState state, String merchantId, Timestamp time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = formatter.format(time);
        this.orderID = UUID.randomUUID().toString().toLowerCase();
        this.originalPrice = Double.parseDouble(String.format("%.2f", originalPrice));
        this.priceAfter = Double.parseDouble(String.format("%.2f", priceAfter));
        this.pointsNeeded = Double.parseDouble(String.format("%.2f", pointsNeeded));
        this.userID = userId;
        this.state = OrderState.getStateString(state);
        this.merchantID = merchantId;
        this.time = timeString;
    }


    public void changeState() {
        if (pointsNeeded > 0 && OrderState.getOrderState(state) == OrderState.TOBEFINISHED) {
            state = OrderState.getStateString(OrderState.SUCCESS);
        } else {
            state = OrderState.getStateString(OrderState.FAIL);
        }
    }

    public void setOrderId(String orderId) {
        this.orderID = orderId;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setPointsNeeded(Double pointsNeeded) {
        this.pointsNeeded = pointsNeeded;
    }

    public void setPriceAfter(Double priceAfter) {
        this.priceAfter = priceAfter;
    }


    public void setUserId(String userId) {
        this.userID = userId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMerchantId(String merchantId) {
        this.merchantID = merchantId;
    }

    public void setTime(Timestamp time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = formatter.format(time);
        this.time = timeString;
    }

    public String getOrderId() {
        return orderID;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public Double getPriceAfter() {
        return priceAfter;
    }

    public Double getPointsNeeded() {
        return pointsNeeded;
    }

    public String getUserId() {
        return userID;
    }

    public OrderState getState() {
        return OrderState.getOrderState(state);
    }

    public String getMerchantId() {
        return merchantID;
    }

    public String getTime() {
        return time;
    }
}
