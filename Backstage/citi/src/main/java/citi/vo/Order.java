package citi.vo;

/*
 * 作者：曹子轩
 * 创建订单实体类
 */


public class Order {
    private String orderId;
    private double originalPrice;
    private double priceAfter;
    private int pointsNeeded;
    private String userId;
    private OrderState state;
    private String merchantId;

    public enum OrderState{
        SUCCESS,FAIL,TOBEFINISHED
    }
    public Order(String orderId, double originalPrice, double priceAfter,String userId, String merchantId) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.priceAfter = priceAfter;
        this.pointsNeeded = -1;
        this.userId = userId;
        this.merchantId = merchantId;
        this.state = OrderState.TOBEFINISHED;
    }

    public void setPointsNeeded(int pointsNeeded) {
        this.pointsNeeded = pointsNeeded;
    }

    public void changeState() {
        if(pointsNeeded>0&&state==OrderState.TOBEFINISHED){
            state = OrderState.SUCCESS;
        }
        else{
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
}
