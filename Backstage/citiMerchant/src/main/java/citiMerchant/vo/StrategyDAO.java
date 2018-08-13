package citiMerchant.vo;

import java.util.UUID;

public class StrategyDAO {
    private String strategyID;
    private String merchantID;
    private Double full;
    private Double priceAfter;
    private Double points;

    public StrategyDAO() {
        this.strategyID= UUID.randomUUID().toString();
    }

    public StrategyDAO(String strategyID){
        this.strategyID=strategyID;
    }

    public StrategyDAO(String strategyID, String merchantID, Double full, Double priceAfter, Double points) {
        this.strategyID = strategyID;
        this.merchantID = merchantID;
        this.full = Double.parseDouble(String.format("%.2f", full));
        this.priceAfter = Double.parseDouble(String.format("%.2f", priceAfter));
        this.points = Double.parseDouble(String.format("%.2f", points));
    }

    public String getStrategyID() {
        return strategyID;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public Double getFull() {
        return full;
    }

    public Double getPriceAfter() {
        return priceAfter;
    }

    public Double getPoints() {
        return points;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public void setStrategyID(String strategyID) {
        this.strategyID = strategyID;
    }

    public void setFull(Double full) {
        this.full = full;
    }

    public void setPriceAfter(Double priceAfter) {
        this.priceAfter = priceAfter;
    }

    public void setPoints(Double points) {
        this.points = points;
    }
}
