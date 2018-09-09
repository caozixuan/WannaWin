package citi.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class UserCoupon {
    protected Long couponID;
    protected String userID;
    protected String ItemID;
    protected String state;
    protected String getTime;
    protected String useTime;

    public enum CouponState {
        USED, UNUSED, OVERDUED;

        static Map<String, UserCoupon.CouponState> enumMap1 = new HashMap<>();
        static Map<UserCoupon.CouponState, String> enumMap2 = new HashMap<>();

        static {
            enumMap1.put("USED", USED);
            enumMap1.put("UNUSED", UNUSED);
            enumMap1.put("OVERDUED", OVERDUED);

            enumMap2.put(USED, "USED");
            enumMap2.put(UNUSED, "UNUSED");
            enumMap2.put(OVERDUED, "OVERDUED");
        }

        public static UserCoupon.CouponState getCouponState(String couponState) {
            return enumMap1.get(couponState);
        }

        public static String getStateString(UserCoupon.CouponState couponState) {
            return enumMap2.get(couponState);
        }

    }

    public Long getCouponID() {
        return couponID;
    }

    public String getGetTime() {
        return getTime;
    }

    public String getUseTime() {
        return useTime;
    }

    public UserCoupon() {

    }

    public UserCoupon(Long couponID, String userID, String itemID, String state, Timestamp getTime, Timestamp useTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String getTimeString = formatter.format(getTime);
        String useTimeString = formatter.format(useTime);
        this.couponID = couponID;
        this.userID = userID;
        ItemID = itemID;
        this.state = state;
        this.getTime = getTimeString;
        this.useTime = useTimeString;
    }

    public UserCoupon(String userID, String itemID, UserCoupon.CouponState state) {
        this.couponID = -1L;
        this.userID = userID;
        ItemID = itemID;
        this.state = CouponState.getStateString(state);
        this.getTime = null;
        this.useTime = null;
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