package citi.vo;

import java.util.HashMap;
import java.util.Map;

public class UserCoupon {
    private long couponID;
    private String userID;
    private String ItemID;
    private String state;

    public enum CouponState {
        USED, UNUSED;

        static Map<String, UserCoupon.CouponState> enumMap1 = new HashMap<>();
        static Map<UserCoupon.CouponState, String> enumMap2 = new HashMap<>();

        static {
            enumMap1.put("USED", USED);
            enumMap1.put("UNUSED", UNUSED);

            enumMap2.put(USED, "USED");
            enumMap2.put(UNUSED, "UNUSED");
        }

        public static UserCoupon.CouponState getCouponState(String couponState) {
            return enumMap1.get(couponState);
        }

        public static String getStateString(UserCoupon.CouponState couponState) {
            return enumMap2.get(couponState);
        }

    }

    public UserCoupon(String userID, String itemID, UserCoupon.CouponState state) {
        this.couponID = -1;
        this.userID = userID;
        ItemID = itemID;
        this.state = CouponState.getStateString(state);
    }

    public String getUserID() {
        return userID;
    }

    public String getItemID() {
        return ItemID;
    }

    public UserCoupon.CouponState getState() {
        return CouponState.getCouponState(state);
    }

}