package citi.funcModule.coupon;

import citi.vo.UserCoupon;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ReturnUserCoupon extends UserCoupon {
    private String itemName;
    private int points;
    private String description;
    private String overdueTime;
    private String logoURL;

    public ReturnUserCoupon(long couponID, String userID, String itemID, String state, String getTime, String useTime, String itemName, int points, String description, String overdueTime, String logoURL) {
        this.couponID = couponID;
        this.userID = userID;
        ItemID = itemID;
        this.state = state;
        this.getTime = getTime;
        this.useTime = useTime;
        this.itemName = itemName;
        this.points=points;
        this.description = description;
        this.overdueTime = overdueTime;
        this.logoURL = logoURL;
    }
}
