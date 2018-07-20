package citiMerchant.vo;

public class StrategyDAO {
    private String strategyID;
    private String merchantID;
    private Double full;
    private Double priceAfter;
    private Double points;

    public StrategyDAO() {

    }

    public StrategyDAO(String strategyID, String merchantID, Double full, Double priceAfter, Double points) {
        this.strategyID = strategyID;
        this.merchantID = merchantID;
        this.full = full;
        this.priceAfter = priceAfter;
        this.points = points;
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

}
