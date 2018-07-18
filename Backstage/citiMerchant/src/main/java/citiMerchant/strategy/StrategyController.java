package citiMerchant.strategy;

import citiMerchant.mapper.MerchantMapper;
import citiMerchant.mapper.StrategyMapper;
import citiMerchant.vo.Strategy;
import citiMerchant.vo.StrategyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhong on 2018/7/11 19:52
 * @author 彭璇
 */
@Controller
public class StrategyController {
    @Autowired
    StrategyService strategyService;

    @Autowired
    MerchantMapper merchantMapper;

    @RequestMapping("/strategy/getStrategyList")
    public ModelAndView getStrategyList(){
        ModelAndView mv = new ModelAndView();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = (String)session.getAttribute("merchantID");
        List<StrategyDAO> strategies = strategyService.getStrategyList(merchantID);
        mv.addObject("merchant",merchantMapper.selectByID(merchantID));
        if(strategies==null)
            mv.addObject("strategies",new ArrayList<StrategyDAO>());
        else
            mv.addObject("strategies",strategies);
        mv.setViewName("strategy/strategyList");
        return mv;
    }

    @RequestMapping("/strategy/deleteStrategy")
    public ModelAndView deleteStrategy(String strategyID){
        ModelAndView mv = new ModelAndView();
        strategyService.deleteStrategy(strategyID);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = (String)session.getAttribute("merchantID");
        List<StrategyDAO> strategies = strategyService.getStrategyList(merchantID);
        mv.addObject("merchant",merchantMapper.selectByID(merchantID));
        if(strategies==null)
            mv.addObject("strategies",new ArrayList<StrategyDAO>());
        else
            mv.addObject("strategies",strategies);
        mv.setViewName("strategy/strategyList");
        return mv;
    }

    @RequestMapping("/strategy/editStrategyRequest")
    public ModelAndView editStrategyRequest(String strategyID){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = (String)session.getAttribute("merchantID");
        ModelAndView mv = new ModelAndView();
        StrategyDAO strategyDAO = strategyService.editStrategyRequest(strategyID);
        mv.addObject("strategy",strategyDAO);
        mv.addObject("merchant",merchantMapper.selectByID(merchantID));
        mv.setViewName("strategy/editStrategy");
        return mv;
    }

    @RequestMapping("/strategy/editStrategySubmit")
    public ModelAndView editStrategySubmit(String strategyID, int full, int priceAfter,int points){
        ModelAndView mv = new ModelAndView();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = (String)session.getAttribute("merchantID");
        StrategyDAO strategyDAO = new StrategyDAO(strategyID,merchantID,full,priceAfter,points);
        strategyService.editStrategySubmit(strategyDAO);
        mv.addObject("merchant",merchantMapper.selectByID(merchantID));
        mv.addObject("strategies",strategyService.getStrategyList(strategyDAO.getMerchantID()));
        mv.setViewName("strategy/strategyList");
        return mv;
    }

    @RequestMapping("/strategy/addStrategyRequest")
    public ModelAndView addStrategyRequest(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/strategy/addStrategy");
        mv.addObject("strategyID",UUID.randomUUID().toString());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = (String)session.getAttribute("merchantID");
        mv.addObject("merchantID",merchantID);
        mv.addObject("merchant",merchantMapper.selectByID(merchantID));
        return mv;
    }

    @RequestMapping("/strategy/addStrategySubmit")
    public ModelAndView addStrategySubmit( int full,int priceAfter,int points){
        ModelAndView mv = new ModelAndView();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session =request.getSession();
        String merchantID = (String)session.getAttribute("merchantID");
        mv.addObject("merchantID",merchantID);
        StrategyDAO strategyDAO = new StrategyDAO(UUID.randomUUID().toString(),merchantID,full,priceAfter,points);
        strategyService.addStrategy(strategyDAO);

        List<StrategyDAO> strategies = strategyService.getStrategyList(merchantID);
        mv.addObject("strategies",strategies);
        mv.addObject("merchant",merchantMapper.selectByID(merchantID));
        mv.setViewName("strategy/strategyList");
        return mv;
    }


}
