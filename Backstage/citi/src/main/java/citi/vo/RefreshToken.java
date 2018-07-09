package citi.vo;

public class RefreshToken {
    private String userID;
    private String refreshToken;
    private String time;

    public RefreshToken(String userID, String refreshToken, String time) {
        this.userID = userID;
        this.refreshToken = refreshToken;
        this.time = time;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
