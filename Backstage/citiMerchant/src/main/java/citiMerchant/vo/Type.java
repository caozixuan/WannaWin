package citiMerchant.vo;

import java.util.HashMap;
import java.util.Map;

public class Type {

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

}
