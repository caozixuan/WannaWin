package citi.login;

import citi.API.VerificationCode;
import citi.vo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 接口设计：刘钟博
 * 代码填充：彭璇
 */
@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    private Gson gson;

    @Autowired
    private LoginSerivce loginSerivce;

    /**
     * 前端请求发送验证码
     * @param phoneNum
     */
    @ResponseBody
    @RequestMapping("/getVCode")
    public String getVCode(String phoneNum){
        loginSerivce.sendMs(phoneNum);
        return "{}";
    }

    /**
     * 验证前端发来的验证码,成功则关联密码创建用户
     * @param phoneNum
     * @param vcode
     * @return 成功：{"isCreate": true｝
     *          失败：{"isCreate": false｝
     */
    @ResponseBody
    @RequestMapping("/sendVCode")
    public String sendVcode(String phoneNum,String vcode,String password){
        boolean isMatch = loginSerivce.vfVcode(phoneNum,vcode,password);
        return "{\"isCreate\": "+isMatch+"}";
    }

    /**
     * 验证登陆
     * @param phoneNum
     * @param password
     * @return 成功:{"userID":"xxxx","generalPoints":0,"availablePoints":0}
     *          失败：{"isLogin":false}
     */
    @ResponseBody
    @RequestMapping("/login")
    public String Login(String phoneNum,String password){
        /*
        do something
        成功返回实例，不需要手动构造json
        User user=new User();
        return gson.toJson(user);
        */
        User user  = loginSerivce.login(phoneNum,password);
        if(user==null){
            return "{}";
        }
        else{
            return gson.toJson(user);
        }

    }


}
