package citiMerchant.strategy;

import citiMerchant.mapper.MerchantMapper;
import citiMerchant.uitl.JsonResult;
import citiMerchant.vo.StrategyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhong on 2018/7/11 19:52
 *
 * @author 彭璇
 */
@Controller
public class StrategyController {
    @Autowired
    StrategyService strategyService;

    @Autowired
    MerchantMapper merchantMapper;

    @RequestMapping("/strategy/getStrategyList")
    public ModelAndView getStrategyList(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        String merchantID = (String) session.getAttribute("merchantID");
        mv.addObject("strategies", strategyService.getStrategyList(merchantID));
        mv.setViewName("table/strategyTable");
        return mv;
    }

    @RequestMapping("/strategy/deleteStrategy")
    public ModelAndView deleteStrategy(String strategyID,HttpSession session) {
        ModelAndView mv = new ModelAndView();
        strategyService.deleteStrategy(strategyID);
        String merchantID = (String) session.getAttribute("merchantID");
        List<StrategyDAO> strategies = strategyService.getStrategyList(merchantID);
        mv.addObject("strategies", strategies);
        mv.setViewName("table/strategyTable");
        return mv;
    }

    @RequestMapping("/strategy/editStrategy")
    public ModelAndView editStrategyRequest(String strategyID) {
        ModelAndView mv = new ModelAndView();
        StrategyDAO strategyDAO = strategyService.editStrategyRequest(strategyID);
        mv.addObject("strategy", strategyDAO);
        mv.setViewName("table/editStrategy");
        return mv;
    }

    @RequestMapping("/strategy/editStrategySubmit")
    @ResponseBody
    public String editStrategySubmit(StrategyDAO strategyDAO,HttpSession session) {
        String merchantID = (String) session.getAttribute("merchantID");
        strategyDAO.setMerchantID(merchantID);
        if (strategyDAO.getStrategyID().equals("")){
            strategyDAO.setStrategyID(UUID.randomUUID().toString());
            strategyService.addStrategy(strategyDAO);
        }else {
            strategyService.editStrategySubmit(strategyDAO);
        }
        return JsonResult.SUCCESS;
    }

    @RequestMapping("/strategy/addStrategy")
    public ModelAndView addStrategyRequest() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/table/editStrategy");
        mv.addObject("strategy",new StrategyDAO(""));
        return mv;
    }

}
