package citi.vo;

import java.sql.Timestamp;

public class Feedback {

    private String userID;
    private String content;
    private Timestamp time;

    public Feedback(String userID, String content, Timestamp time) {
        this.userID = userID;
        this.content = content;
        this.time = time;
    }

    public String getUserID() {
        return userID;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getTime() {
        return time;
    }

}
