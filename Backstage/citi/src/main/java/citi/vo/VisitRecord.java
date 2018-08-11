package citi.vo;

import java.sql.Timestamp;

public class VisitRecord {

    private String userID;
    private String itemID;
    private Timestamp time;

    public VisitRecord(String userID, String itemID, Timestamp time) {
        this.userID = userID;
        this.itemID = itemID;
        this.time = time;
    }

    public String getUserID() {
        return userID;
    }

    public String getItemID() {
        return itemID;
    }

    public Timestamp getTime() {
        return time;
    }

}