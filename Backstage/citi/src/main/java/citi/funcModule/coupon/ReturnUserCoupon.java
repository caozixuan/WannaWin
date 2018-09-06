package citi.funcModule.coupon;

import citi.vo.UserCoupon;

import java.sql.Timestamp;

public class ReturnUserCoupon extends UserCoupon {
    private String itemName;
    private int points;
    private String description;
    private String overdueTime;
    private String logoURL;

    public ReturnUserCoupon(long couponID, String userID, String itemID, String state, String getTime, String useTime, String itemName, int points, String description, String overdueTime, String logoURL) {
        super(couponID, userID, itemID, state, Timestamp.valueOf(getTime), Timestamp.valueOf(useTime));
        this.itemName = itemName;
        this.points=points;
        this.description = description;
        this.overdueTime = overdueTime;
        this.logoURL = logoURL;
    }
}
