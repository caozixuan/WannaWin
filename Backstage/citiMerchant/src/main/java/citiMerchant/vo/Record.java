package citiMerchant.vo;

public class Record {

    public String IN_MerchantID;
    public int IN_intervalDate;
    public long totalPoints;

    public Record(long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Record(String IN_MerchantID, int IN_intervalDate) {
        this.IN_MerchantID = IN_MerchantID;
        this.IN_intervalDate = IN_intervalDate;
        this.totalPoints = -1;
    }

}
