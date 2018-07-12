package citiMerchant.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DBHandler {
    @Autowired
    public static OrderMapper orderMapper;
    @Autowired
    public static ItemMapper itemMapper;
    @Autowired
    public static StrategyMapper strategyMapper;

    static public ArrayList<Integer> getAmountByMerchantID(String merchantID) {
        int itemAmount = itemMapper.getItemAmountByMerchantID(merchantID);
        int stategyAmount = strategyMapper.getStrategyAmountByMerchantID(merchantID);
        int orderAmount = orderMapper.getOrderAmount(merchantID);
        List<Integer> amount = Arrays.asList(itemAmount, stategyAmount, orderAmount);
        return new ArrayList(amount);
    }


}
