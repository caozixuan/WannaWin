package citi.vo;

public class Strategy {
    private String strategyID;
    private String MerchantID;
    private Double full;
    private Double priceAfter;
    private Double points;

    public Strategy(String strategyID, String merchantID, Double full, Double priceAfter, Double points) {
        this.strategyID = strategyID;
        this.MerchantID = merchantID;
        this.full = Double.parseDouble(String.format("%.2f", full));
        this.priceAfter = Double.parseDouble(String.format("%.2f", priceAfter));
        this.points = Double.parseDouble(String.format("%.2f", points));
    }

    public Strategy(String strategyID, String merchantID, int full, int priceAfter, int points) {
        this.strategyID = strategyID;
        this.MerchantID = merchantID;
        this.full = (double)full;
        this.priceAfter =(double) priceAfter;
        this.points = (double)points;
    }

    public String getStrategyID() {
        return strategyID;
    }

    public String getMerchantID() {
        return MerchantID;
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
