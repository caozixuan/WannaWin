package citiMerchant.vo;

public class StrategyDAO {
    private String strategyID;
    private String merchantID;
    private int full;
    private int priceAfter;
    private int points;

    public StrategyDAO() {

    }

    public StrategyDAO(String strategyID, String merchantID, int full, int priceAfter, int points) {
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

    public int getFull() {
        return full;
    }

    public int getPriceAfter() {
        return priceAfter;
    }

    public int getPoints() {
        return priceAfter;
    }

}
