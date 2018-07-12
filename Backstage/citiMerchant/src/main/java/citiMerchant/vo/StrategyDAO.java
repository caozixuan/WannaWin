package citiMerchant.vo;

public class StrategyDAO {
    private String strategyID;
    private String MerchantID;
    private int full;
    private int discount;
    private int points;

    public StrategyDAO(String strategyID, String merchantID, int full, int discount, int points) {
        this.strategyID = strategyID;
        MerchantID = merchantID;
        this.full = full;
        this.discount = discount;
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

    public int getDiscount() {
        return discount;
    }

    public int getPoints() {
        return points;
    }

}
