package citi.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class VisitRecord {

    private String userID;
    private String itemID;
    private String time;

    public VisitRecord(String userID, String itemID, Timestamp time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = formatter.format(time);
        this.userID = userID;
        this.itemID = itemID;
        this.time = timeString;
    }

    public String getUserID() {
        return userID;
    }

    public String getItemID() {
        return itemID;
    }

    public String getTime() {
        return time;
    }

}