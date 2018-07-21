package citi.persist.procedure.probean;

public class Record {

    private String IN_MerchantID;
    private Integer IN_intervalDate;
    private Long totalPoints;

    public Record(String IN_MerchantID, Integer IN_intervalDate) {
        this.IN_MerchantID = IN_MerchantID;
        this.IN_intervalDate = IN_intervalDate;
        this.totalPoints = -1L;
    }

    public String getIN_MerchantID() {
        return IN_MerchantID;
    }

    public Integer getIN_intervalDate() {
        return IN_intervalDate;
    }

    public Long getTotalPoints() {
        return totalPoints;
    }

}
