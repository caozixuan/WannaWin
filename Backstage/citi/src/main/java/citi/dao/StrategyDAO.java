package citi.dao;

public class StrategyDAO {
    private String CardTypeID;
    private int full;
    private int discount;

    public StrategyDAO(String cardTypeID, int full, int discount) {
        CardTypeID = cardTypeID;
        this.full = full;
        this.discount = discount;
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

}
