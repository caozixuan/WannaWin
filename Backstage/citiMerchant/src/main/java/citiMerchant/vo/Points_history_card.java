package citiMerchant.vo;

public class Points_history_card {
    private String IN_userID;
    private String IN_merchantID;
    private int IN_intervalDate;
    private long totalPoints_card;
    private long totalPoints_citi;

    public Points_history_card(String IN_userID, String IN_merchantID, int IN_intervalDate) {
        this.IN_userID = IN_userID;
        this.IN_merchantID = IN_merchantID;
        this.IN_intervalDate = IN_intervalDate;
        this.totalPoints_card = -1;
        this.totalPoints_citi = -1;
    }

    public String getIN_userID() {
        return IN_userID;
    }

    public String getIN_merchantID() {
        return IN_merchantID;
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
