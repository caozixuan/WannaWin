package citiMerchant.login;

import citiMerchant.showData.Prepare_info;
import citiMerchant.showData.Session_store;
import citiMerchant.uitl.JsonResult;
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


/**
 * Created by zhong on 2018/7/11 19:51
 *
 * @author 彭璇
 */
@Controller

public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/loginSubmit")
    @ResponseBody
    public String loginSubmit(String merchantID, String password,HttpSession session) {
        if (loginService.login(merchantID,password)==null){
            return JsonResult.FAIL;
        }else {
            session.setAttribute("merchantID", merchantID);
            session.setAttribute("password", password);

            //prepare for statistics information to display
            Session_store.addSession(merchantID, session);
            Prepare_info prepare = new Prepare_info();
            prepare.set(merchantID);
            new Thread(prepare).start();

            return JsonResult.SUCCESS;
        }
    }

    @RequestMapping("/logout")
    public String logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().invalidate();
        return "redirect:/login.html";
    }

/*    @RequestMapping("/starter")
    public ModelAndView starter() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String merchantID = (String) session.getAttribute("merchantID");
        String password = (String) session.getAttribute("password");
        return loginSubmit(merchantID, password);
    }*/


}
