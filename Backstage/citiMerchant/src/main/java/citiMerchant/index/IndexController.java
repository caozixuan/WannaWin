package citiMerchant.index;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/index")
    public ModelAndView index(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        String merchantID = (String)session.getAttribute("merchantID");
        modelAndView.addObject("merchant",indexService.getMerchant(merchantID));
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
