package citi.vo;

public class StrategyDAO {
    private String strategyID;
    private String MerchantID;
    private Integer full;
    private Integer priceAfter;
    private Integer points;

    public StrategyDAO(String strategyID, String merchantID, Integer full, Integer priceAfter, Integer points) {
        this.strategyID = strategyID;
        MerchantID = merchantID;
        this.full = full;
        this.priceAfter = priceAfter;
        this.points = points;
    }

    public String getStrategyID() {
        return strategyID;
    }

    public String getMerchantID() {
        return MerchantID;
    }

    public Integer getFull() {
        return full;
    }

    public Integer getPriceAfter() {
        return priceAfter;
    }

    public Integer getPoints() {
        return points;
    }

}
