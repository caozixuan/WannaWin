import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//The "Type" is used in "userPref" and "item"
public class Type {

    static final private int radix = 16;
    static final private int amount = 512;
    static final private int long_bit = 64;
    static final private int number_of_bits_in_byte = 4;                                              // f = 1111
    static final private int number_of_bytes_in_str = 512 / number_of_bits_in_byte;                   // 128, str.length
    static final private int numbers_of_bytes_in_long_str = long_bit / number_of_bits_in_byte;        // 16
    static final private int length_of_longs = amount / long_bit;                                     // 8


    /*
     * The amount is less than #{amount}, so it can be interpreted as #{amount} bits.
     * 0 ~ (#{amount} -1) bit represents types.
     *
     * The Conversion Chain is like:
     *          enum => str => bit => enum
     *
     */
    public enum ItemType {
        normal, catering, exercise, bank, costume, education, communication;

        static Map<ItemType, String> m1 = new HashMap<>();      //  enum  =>  str
        static Map<String, Integer> m2 = new HashMap<>();       //  str   =>  bit
        static Map<Integer, ItemType> m3 = new HashMap<>();     //  bit   =>  enum

        static {

            //  enum  =>  str
            m1.put(normal, "normal");
            m1.put(catering, "catering");
            m1.put(exercise, "exercise");
            m1.put(bank, "bank");
            m1.put(costume, "costume");
            m1.put(education, "education");
            m1.put(communication, "communication");


            //  str   =>  bit
            Integer bit = 0;
            m2.put("normal", bit++);
            m2.put("catering", bit++);
            m2.put("exercise", bit++);
            m2.put("bank", bit++);
            m2.put("costume", bit++);
            m2.put("education", bit++);
            m2.put("communication", bit++);


            //  bit   =>  enum
            bit = 0;
            m3.put(bit++, normal);
            m3.put(bit++, catering);
            m3.put(bit++, exercise);
            m3.put(bit++, bank);
            m3.put(bit++, costume);
            m3.put(bit++, education);
            m3.put(bit++, communication);


        }

        // str => bit => enum
        public static ItemType str2enum(String itemType) {
            return m3.get(m2.get(itemType));
        }

        // enum => str => bit
        public static Integer enum2bit(ItemType itemType) {
            return m2.get(m1.get(itemType));
        }

        // bit => enum
        public static ItemType bit2enum(Integer bit) {
            return m3.get(bit);
        }

        // bit => enum => str
        public static String bit2str(Integer bit) {
            return m1.get(m3.get(bit));
        }

        // enum => str
        public static String enum2str(ItemType itemType) {
            return m1.get(itemType);
        }

    }


    /*
     * TypeWrapper stores the typeStr
     *      and provide : 1. conversion operations  (DBStr <=> TypeWrapper)
     *                    2. update operations
     *                    3. get feature Vector
     */
    public static class TypeWrapper {

        private Long[] types;

        //typeString is stored in DB
        public TypeWrapper(String typeString) {
            types = Str2Long(typeString);
        }


        /*
         * return Boolean[#{amount}],
         *              which represents the types involving itemTypes in Item or UserPref.
         */
        public Boolean[] getFeatureVec() {
            Boolean[] b = new Boolean[amount];
            for (int i = 0; i < length_of_longs; ++i)
                for (int j = 0; j < long_bit; ++j)
                    b[long_bit * i + j] = ((types[i] & (1L << j)) == (1L << j)) ? true : false;
            return b;
        }

        /*
         * set the dth bit to '1'
         * 0 <= dimension < #{amount}
         */
        public void addType(int dimension) {
            assert (dimension < amount && dimension >= 0) : "Invalid dimension!";
            int q = dimension / long_bit;
            int r = dimension - q * long_bit;
            types[q] |= (1L << r);
        }


        /*
         * set the dth bit to '0'
         * 0 <= dimension < #{amount}
         */
        public void eraseType(int dimension) {
            assert (dimension < amount && dimension >= 0) : "Invalid dimension!";
            int q = dimension / long_bit;
            int r = dimension - q * long_bit;
            types[q] &= ~(1L << r);
        }


        //To store in DB
        @Override
        public String toString() {
            String s = new String();
            for (Long l : types) {
                String temp = Long.toUnsignedString(l, radix);
                //fill up the leading "0" in each "Long" // ex. 123 => "0123"
                s = String.join("", Collections.nCopies(numbers_of_bytes_in_long_str - temp.length(), "0")) + temp + s;
            }
            return s;
        }


    }


    /*
     * Parameter: #{number_of_bytes_in_str} bytes String : "(ffffffffffffffffff)(....)(abcd...)(0123...)"
     * Return:
     *          Long[#{length_of_longs}] = (0123...), (abcd...), ..., (ffffffffffffffffff)
     */
    private static Long[] Str2Long(final String type) {
        Long[] l = new Long[length_of_longs];
        try {
            for (int i = 0; i < length_of_longs; ++i)
                l[length_of_longs - 1 - i] = Long.parseUnsignedLong(type.substring(0 + numbers_of_bytes_in_long_str * i, numbers_of_bytes_in_long_str + numbers_of_bytes_in_long_str * i), radix);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return l;
    }


}