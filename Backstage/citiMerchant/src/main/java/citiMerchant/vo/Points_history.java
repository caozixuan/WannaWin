package citiMerchant.vo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Points_history {
    private String userID;
    private String merchantID;
    private Integer points_card;
    private Double points_citi;
    private String cause;
    private Timestamp time;

    public Points_history(String userID, String merchantIDs, Integer points_card, Double points_citi, String cause, Timestamp time) {
        this.userID = userID;
        this.merchantID = merchantID;
        this.points_card = points_card;
        this.points_citi = points_citi;
        this.cause = cause;
        this.time = time;
    }

    public boolean isEXCHANGE() {
        return Cause.getCause(cause) == Cause.EXCHANGE;
    }

    public String getUserID() {
        return userID;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public Integer getPoints_card() {
        return points_card;
    }

    public Double getPoints_citi() {
        return points_citi;
    }

    public Cause getCause() {
        return Cause.getCause(cause);
    }

    public Timestamp getTime() {
        return time;
    }

    public enum Cause {
        GAIN, EXCHANGE;

        static Map<String, Cause> enumMap1 = new HashMap<>();
        static Map<Cause, String> enumMap2 = new HashMap<>();

        static {
            enumMap1.put("GAIN", GAIN);
            enumMap1.put("EXCHANGE", EXCHANGE);

            enumMap2.put(GAIN, "GAIN");
            enumMap2.put(EXCHANGE, "EXCHANGE");
        }

        public static Cause getCause(String cause) {
            return enumMap1.get(cause);
        }

        public static String getCause(Cause cause) {
            return enumMap2.get(cause);
        }

    }


}
