package citi.vo;

public class UserCoupon_record {
    private String IN_userID;
    private String IN_itemID;
    private Integer ifUsed;

    public UserCoupon_record(String IN_userID, String IN_itemID, Integer ifUsed) {
        this.IN_userID = IN_userID;
        this.IN_itemID = IN_itemID;
        this.ifUsed = ifUsed;
    }

    public UserCoupon_record(String IN_userID, String IN_itemID) {
        this.IN_userID = IN_userID;
        this.IN_itemID = IN_itemID;
        this.ifUsed = 0;
    }

    public String getIN_userID() {
        return IN_userID;
    }

    public void setIN_userID(String IN_userID) {
        this.IN_userID = IN_userID;
    }

    public String getIN_itemID() {
        return IN_itemID;
    }

    public void setIN_itemID(String IN_itemID) {
        this.IN_itemID = IN_itemID;
    }

    public Integer getIfUsed() {
        return ifUsed;
    }

    public void setIfUsed(Integer ifUsed) {
        this.ifUsed = ifUsed;
    }

}
