package citiMerchant.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBhandler {

    static public ArrayList<Integer> getAmountByMerchantID(String merchantID) {

        List<Integer> amount = Arrays.asList(1, 2, 3);
        ArrayList<Integer> nums = new ArrayList(amount);
        return nums;
    }


}
