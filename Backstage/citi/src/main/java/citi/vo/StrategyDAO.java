package citi.vo;

public class StrategyDAO {
    private String strategyID;
    private String MerchantID;
    private int full;
    private int priceAfter;
    private int points;

    public StrategyDAO(String strategyID, String merchantID, int full, int priceAfter, int points) {
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

    public int getFull() {
        return full;
    }

    public int getPriceAfter() {
        return priceAfter;
    }

    public int getPoints() {
        return points;
    }

}
