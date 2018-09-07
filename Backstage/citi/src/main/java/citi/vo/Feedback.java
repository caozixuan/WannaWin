package citi.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Feedback {

    private String userID;
    private String content;
    private String time;

    public Feedback(String userID, String content, Timestamp time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String timeString = formatter.format(time);
        this.userID = userID;
        this.content = content;
        this.time = timeString;
    }

    public String getUserID() {
        return userID;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

}
