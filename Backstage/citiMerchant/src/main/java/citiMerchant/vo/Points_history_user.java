package citiMerchant.vo;

public class Points_history_user {
    private String IN_userID;
    private Integer IN_intervalDate;
    private Long totalPoints_card;
    private Long totalPoints_citi;

    public Points_history_user(String IN_userID, Integer IN_intervalDate) {
        this.IN_userID = IN_userID;
        this.IN_intervalDate = IN_intervalDate;
        this.totalPoints_card = -1L;
        this.totalPoints_citi = -1L;
    }

    public String getIN_userID() {
        return IN_userID;
    }

    public Integer getIN_intervalDate() {
        return IN_intervalDate;
    }

    public Long getTotalPoints_card() {
        return totalPoints_card;
    }

    public Long getTotalPoints_citi() {
        return totalPoints_citi;
    }

}
