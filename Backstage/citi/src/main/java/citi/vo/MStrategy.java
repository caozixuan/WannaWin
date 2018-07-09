package citi.vo;

public class MStrategy {
    private String merchantId;
    private int full;
    private int discount;

    public MStrategy(String merchantId, int full, int discount) {
        this.merchantId = merchantId;
        this.full = full;
        this.discount = discount;
    }

    public MStrategy() {
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public int getFull() {
        return full;
    }

    public void setFull(int full) {
        this.full = full;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
