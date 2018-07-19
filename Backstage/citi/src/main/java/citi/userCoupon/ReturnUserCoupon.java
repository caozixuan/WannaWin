package citi.userCoupon;

import citi.vo.UserCoupon;

import java.sql.Timestamp;

public class ReturnUserCoupon extends UserCoupon {
    private String itemName;
    private int points;
    private String description;

    public ReturnUserCoupon(long couponID, String userID, String itemID, String state, Timestamp getTime, Timestamp useTime, String itemName, int points, String description) {
        super(couponID, userID, itemID, state, getTime, useTime);
        this.itemName = itemName;
        this.points=points;
        this.description = description;
    }
}
