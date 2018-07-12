package citiMerchant.login;

import citiMerchant.vo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *Created by zhong on 2018/7/11 19:51
 */
@Controller

public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login/login");
        return mv;
    }

    @RequestMapping("/loginSubmit")
    public ModelAndView loginSubmit(String username,String password){

        return "";
    }




}
