package citi.userCoupon;

import citi.vo.UserCoupon;

import java.sql.Timestamp;

public class ReturnUserCoupon extends UserCoupon {
    private String itemName;

    public ReturnUserCoupon(long couponID, String userID, String itemID, String state, Timestamp getTime, Timestamp useTime, String itemName) {
        super(couponID, userID, itemID, state, getTime, useTime);
        this.itemName = itemName;
    }
}
