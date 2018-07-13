package citiMerchant.login;

import citiMerchant.vo.Merchant;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


/**
 *Created by zhong on 2018/7/11 19:51
 *@author  彭璇
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
    public ModelAndView loginSubmit(String merchantID,String password){
        ModelAndView mv = new ModelAndView();
        ArrayList<Integer> nums = loginService.getNums(merchantID,password);
        if(nums==null){
            mv.setViewName("login/fail");
            return mv;
        }
        else{
            mv.addObject("nums",nums);
            mv.setViewName("starter");
            return mv;
        }

    }
    @RequestMapping("/starter")
    public ModelAndView starter(String merchantID,String password){
        return loginSubmit(merchantID,password);
    }





}
