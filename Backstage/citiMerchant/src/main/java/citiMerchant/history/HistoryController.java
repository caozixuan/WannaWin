package citiMerchant.history;

import citiMerchant.mapper.MerchantMapper;
import citiMerchant.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/7/11 19:51
 * @author 彭璇
 */

@Controller
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @Autowired
    private MerchantMapper merchantMapper;

    @RequestMapping("/history")
    public ModelAndView getHistory(HttpSession session){
        ModelAndView mv = new ModelAndView();
        String merchantID = (String)session.getAttribute("merchantID");
        List<Order> orders = historyService.getHistory(merchantID);
        mv.addObject("orders",orders);
        mv.setViewName("table/history");
        return mv;
    }

}
