package citiMerchant.vo;

import java.sql.Timestamp;

public class RefreshToken {
    private String userID;
    private String refreshToken;
    private Timestamp time;

    public RefreshToken(String userID, String refreshToken, Timestamp time) {
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
