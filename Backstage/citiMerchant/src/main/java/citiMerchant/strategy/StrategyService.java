package citiMerchant.strategy;

import citiMerchant.mapper.StrategyMapper;
import citiMerchant.vo.Strategy;
import citiMerchant.vo.StrategyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/7/11 19:52
 * @author  彭璇
 */
@Service
public class StrategyService {
    @Autowired StrategyMapper strategyMapper;

    public List<StrategyDAO> getStrategyList(String merchantID){
        return strategyMapper.getStrategysByMerchantID(merchantID);
    }

    public void deleteStrategy(String strategyID){
        strategyMapper.deleteStrategy(strategyID);
    }

    public StrategyDAO editStrategyRequest(String strategyID){
        return   strategyMapper.getStrategyByStrategyID(strategyID);
    }

    public void editStrategySubmit(StrategyDAO strategyDAO){
        int i = strategyMapper.updateStrategy(strategyDAO.getStrategyID(),strategyDAO.getFull(),strategyDAO.getPriceAfter(),strategyDAO.getPoints());
        System.out.println(i);
    }

    public void addStrategy(StrategyDAO strategyDAO){
        strategyMapper.addStrategy(strategyDAO);
    }
}
