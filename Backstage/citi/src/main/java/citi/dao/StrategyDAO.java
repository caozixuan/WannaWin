package citi.dao;

public class StrategyDAO {
    private String CardTypeID;
    private int full;
    private int discount;
    private double points;

    public StrategyDAO(String cardTypeID, int full, int discount, double points) {
        CardTypeID = cardTypeID;
        this.full = full;
        this.discount = discount;
        this.points = points;
    }

    public String getCardTypeID() {
        return CardTypeID;
    }

    public int getFull() {
        return full;
    }

    public int getDiscount() {
        return discount;
    }

    public double getPoints() {
        return points;
    }

}
