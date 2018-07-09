package citi.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthIeterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userID=(String) request.getSession().getAttribute("userID");
        //System.out.println(request.getParameter("phoneNum"));
        if (userID==null){
            return false;
        }
        return true;
    }
}
