package citiMerchant.history;

import citiMerchant.mapper.OrderMapper;
import citiMerchant.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/7/11 19:51
 * @author 彭璇
 */

@Service
public class HistoryService {



    @Autowired
    OrderMapper orderMapper;

    public List<Order> getHistory(String merchantID){
        return orderMapper.getOrderByMerchantID(merchantID,"101010101010");
    }

}
