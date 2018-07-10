package citi.dao;

/*
 * 作者：曹子轩
 * 创建订单实体类
 */


public class OrderDAO {
    private String orderId;
    private double originalPrice;
    private double priceAfter;
    private int pointsNeeded;
    private String userId;
    private OrderState state;
    private String merchantId;
    private String time;

    public enum OrderState {
        SUCCESS, FAIL, TOBEFINISHED
    }

    public OrderDAO(String orderId, double originalPrice, double priceAfter, int pointsNeeded, String userId, OrderState state, String merchantId, String time) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.priceAfter = priceAfter;
        this.pointsNeeded = pointsNeeded;
        this.userId = userId;
        this.state = state;
        this.merchantId = merchantId;
        this.time = time;
    }

    public void setPointsNeeded(int pointsNeeded) {
        this.pointsNeeded = pointsNeeded;
    }

    public void changeState() {
        if (pointsNeeded > 0 && state == OrderState.TOBEFINISHED) {
            state = OrderState.SUCCESS;
        } else {
            state = OrderState.FAIL;
        }
    }

    public String getOrderId() {
        return orderId;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getPriceAfter() {
        return priceAfter;
    }

    public int getPointsNeeded() {
        return pointsNeeded;
    }

    public String getUserId() {
        return userId;
    }

    public OrderState getState() {
        return state;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getTime() {
        return time;
    }
}
