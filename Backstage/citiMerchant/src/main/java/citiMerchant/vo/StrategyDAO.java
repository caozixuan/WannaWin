package citiMerchant.vo;

public class StrategyDAO {
    private String strategyID;
    private String merchantID;
    private int full;
    private int discount;
    private int points;

    public StrategyDAO() {

    }

    public StrategyDAO(String strategyID, String merchantID, int full, int discount, int points) {
        this.strategyID = strategyID;
        this.merchantID = merchantID;
        this.full = full;
        this.discount = discount;
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

    public int getDiscount() {
        return discount;
    }

    public int getPoints() {
        return points;
    }

}
