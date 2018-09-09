package citiMerchant.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhong on 2018/7/16 8:30
 */
public class AuthInterceptorAdapter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String merchantID=(String)request.getSession().getAttribute("merchantID");
        if (merchantID==null){
            return false;
        }else {
            return true;
        }
    }
}
