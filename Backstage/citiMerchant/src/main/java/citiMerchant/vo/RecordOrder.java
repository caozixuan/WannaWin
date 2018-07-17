package citiMerchant.vo;

import java.sql.Date;

public class RecordOrder {

    private String IN_MerchantID;
    private Date IN_start_date;
    private Date IN_end_date;
    private Long totalPoints;

    public RecordOrder(String IN_MerchantID, Date IN_start_date, Date IN_end_date) {
        this.IN_MerchantID = IN_MerchantID;
        this.IN_start_date = IN_start_date;
        this.IN_end_date = IN_end_date;
        this.totalPoints = -1L;
    }

    public String getIN_MerchantID() {
        return IN_MerchantID;
    }

    public Date getIN_start_date() {
        return IN_start_date;
    }

    public Date getIN_end_date() {
        return IN_end_date;
    }

    public Long getTotalPoints() {
        return totalPoints;
    }
}
