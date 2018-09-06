package citi.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Points_history {
    protected String userID;
    protected String merchantID;
    protected Integer points_card;
    protected Double points_citi;
    protected String cause;
    protected String time;

    public Points_history(String userID, String merchantIDs, Integer points_card, Double points_citi, String cause, Timestamp time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String timeString = formatter.format(time);
        this.userID = userID;
        this.merchantID = merchantID;
        this.points_card = points_card;
        this.points_citi = Double.parseDouble(String.format("%.2f", points_citi));
        this.cause = cause;
        this.time = timeString;
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

    public String getTime() {
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
