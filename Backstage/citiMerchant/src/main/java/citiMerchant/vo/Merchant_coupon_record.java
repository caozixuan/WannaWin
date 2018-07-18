package citiMerchant.vo;

import java.math.BigDecimal;

public class Merchant_coupon_record {
    private String name;
    private Integer totalPoints;

    public Merchant_coupon_record(String name, Integer totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;
    }

    public Merchant_coupon_record(String name, BigDecimal totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints.intValueExact();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }
}
