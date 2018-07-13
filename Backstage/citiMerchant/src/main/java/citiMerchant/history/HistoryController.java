package citiMerchant.history;

import citiMerchant.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/history")
    public ModelAndView getHistory(String merchantID){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("history/history");
        List<Order> orders = historyService.getHistory(merchantID);
        if(orders==null)
            mv.addObject("orders", new ArrayList<Order>());
        else
            mv.addObject("orders",orders);
        return mv;
    }

}
