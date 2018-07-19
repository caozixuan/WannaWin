package citiMerchant.vo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private String orderId;
    private Double originalPrice;
    private Double priceAfter;
    private Double pointsNeeded;
    private String userId;
    private String state;   //2018-07-10 从 OrderState 改为 String - 任思远
    private String merchantId;
    private Timestamp time;

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
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.priceAfter = priceAfter;
        this.pointsNeeded = pointsNeeded;
        this.userId = userId;
        this.state = state;
        this.merchantId = merchantId;
        this.time = time;
    }

    public Order(String orderId, Double originalPrice, Double priceAfter, Double pointsNeeded, String userId, OrderState state, String merchantId, Timestamp time) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.priceAfter = priceAfter;
        this.pointsNeeded = pointsNeeded;
        this.userId = userId;
        this.state = OrderState.getStateString(state);
        this.merchantId = merchantId;
        this.time = time;
    }

    public void setPointsNeeded(Double pointsNeeded) {
        this.pointsNeeded = pointsNeeded;
    }

    public void changeState() {
        if (pointsNeeded > 0 && OrderState.getOrderState(state) == OrderState.TOBEFINISHED) {
            state = OrderState.getStateString(OrderState.SUCCESS);
        } else {
            state = OrderState.getStateString(OrderState.FAIL);
        }
    }

    public String getOrderId() {
        return orderId;
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
        return userId;
    }

    public OrderState getState() {
        return OrderState.getOrderState(state);
    }

    public String getMerchantId() {
        return merchantId;
    }

    public Timestamp getTime() {
        return time;
    }
}
