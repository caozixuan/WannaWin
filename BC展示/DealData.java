import java.util.HashMap;
import java.util.Map;

public class DealData {

    /*
     * Strig type will be either "IN" or "OUT",
     *     "IN"  :
     *             points_citi up     ( points_card => points_citi )
     *     "OUT" :
     *             points_citi down   ( points_citi => item )
     */
    final private String type;
    final public String merchantID;
    final private String userID;
    final private Double points_citi;

    public enum DealType {
        IN, OUT;

        static Map<String, DealType> enumMap1 = new HashMap<>();
        static Map<DealType, String> enumMap2 = new HashMap<>();

        static {
            enumMap1.put("IN", IN);
            enumMap1.put("OUT", OUT);

            enumMap2.put(IN, "IN");
            enumMap2.put(OUT, "OUT");
        }

        public static DealType getType(String typeString) {
            return enumMap1.get(typeString);
        }

        public static String getTypeString(DealType type) {
            return enumMap2.get(type);
        }

    }

    public DealData(DealType type, String merchantID, String userID, Double points_citi) {
        this.type = DealType.getTypeString(type);
        this.merchantID = merchantID;
        this.userID = userID;
        this.points_citi = points_citi;
    }

    public String encrypt() {
        String pub_K = Encrypt.getPubK(merchantID);
        if (pub_K == null) {
            System.err.println("The merchant with ID: \"" + merchantID + "\" has not registered!");
            return null;
        }
        String message = type + "," + userID + "," + points_citi.toString();
        return RSA.encryptByPublic(message, pub_K);
    }

}
