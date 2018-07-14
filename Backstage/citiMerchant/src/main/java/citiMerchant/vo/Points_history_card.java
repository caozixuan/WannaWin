package citiMerchant.vo;

public class Points_history_card {
    private String IN_cardID;
    private int IN_intervalDate;
    private long totalPoints_card;
    private long totalPoints_citi;

    public Points_history_card(String IN_cardID, int IN_intervalDate) {
        this.IN_cardID = IN_cardID;
        this.IN_intervalDate = IN_intervalDate;
        this.totalPoints_card = -1;
        this.totalPoints_citi = -1;
    }

    public Points_history_card(String IN_cardID, int IN_intervalDate, long totalPoints_card, long totalPoints_citi) {
        this.IN_cardID = IN_cardID;
        this.IN_intervalDate = IN_intervalDate;
        this.totalPoints_card = totalPoints_card;
        this.totalPoints_citi = totalPoints_citi;
    }

    public String getIN_cardID() {
        return IN_cardID;
    }

    public int getIN_intervalDate() {
        return IN_intervalDate;
    }

    public long getTotalPoints_card() {
        return totalPoints_card;
    }

    public long getTotalPoints_citi() {
        return totalPoints_citi;
    }

}
