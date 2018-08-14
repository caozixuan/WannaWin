package citi.vo;

import java.util.HashMap;
import java.util.Map;

public class Type {


    //The amount is less than 512, so it can be interpreted as 512 bits.
    // 0 ~ 511 bit represents types.
    //
    public enum ItemType {
        normal, catering, exercise, bank, costume, education, communication;

        static Map<String, ItemType> enumMap1 = new HashMap<>();
        static Map<ItemType, String> enumMap2 = new HashMap<>();

        static {
            enumMap1.put("normal", normal);
            enumMap1.put("catering", catering);
            enumMap1.put("exercise", exercise);
            enumMap1.put("bank", bank);
            enumMap1.put("costume", costume);
            enumMap1.put("education", education);
            enumMap1.put("communication", communication);

            enumMap2.put(normal, "normal");
            enumMap2.put(catering, "catering");
            enumMap2.put(exercise, "exercise");
            enumMap2.put(bank, "bank");
            enumMap2.put(costume, "costume");
            enumMap2.put(education, "education");
            enumMap2.put(communication, "communication");
        }

        public static ItemType getItemType(String itemType) {
            return enumMap1.get(itemType);
        }

        public static String getItemTypeString(ItemType itemType) {
            return enumMap2.get(itemType);
        }

    }


    public class type {





    }


    /*
     * Parameter: 128 bytes String : "(ffffffffffffffffff)(....)(abcd...)(0123...)"
     * Return:
     *          Long[8] = (0123...), (abcd...), ..., ffffffffffffffffff
     **/
    public static Long[] Str2Long(final String type) {
        Long[] l = new Long[8];
        try {
            for (int i = 0; i < 8; i++) {
                int k = 7 - i;
                l[k] = Long.parseLong(type.substring(0 + 16 * k, 16 + 16 * k), 16);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return l;
    }


}
