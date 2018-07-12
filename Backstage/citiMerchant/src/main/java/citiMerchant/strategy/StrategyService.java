package citiMerchant.strategy;

import citiMerchant.mapper.StrategyMapper;
import citiMerchant.vo.Strategy;
import citiMerchant.vo.StrategyDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/7/11 19:52
 * @author  彭璇
 */
public class StrategyService {
    @Autowired StrategyMapper strategyMapper;

    public List<StrategyDAO> getStrategyList(String merchantID){
        return strategyMapper.getStrategysByMerchantID(merchantID);
    }

    public void deleteStrategy(String strategyID){
        strategyMapper.deleteStrategy(strategyID);
    }

    public void updateStrategy(StrategyDAO strategyDAO){
        strategyMapper.updateStrategy(strategyDAO);
    }

    public void addStrategy(StrategyDAO strategyDAO){
        strategyMapper.addStrategy(strategyDAO);
    }
}
