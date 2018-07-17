package citi.vo;

public class Record {

    private String IN_MerchantID;
    private int IN_intervalDate;
    private Long totalPoints;

    public Record(String IN_MerchantID, int IN_intervalDate) {
        this.IN_MerchantID = IN_MerchantID;
        this.IN_intervalDate = IN_intervalDate;
        this.totalPoints = -1L;
    }

    public String getIN_MerchantID() {
        return IN_MerchantID;
    }

    public int getIN_intervalDate() {
        return IN_intervalDate;
    }

    public Long getTotalPoints() {
        return totalPoints;
    }

}
