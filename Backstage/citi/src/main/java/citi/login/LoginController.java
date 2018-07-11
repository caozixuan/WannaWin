package citi.login;

import citi.vo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 接口设计：刘钟博
 * 代码填充：彭璇
 * bug修复：刘钟博
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
     * @url /login/getVCode
     * @param phoneNum 请求手机号
     * @return {} 成功与否都返回空
     */
    @ResponseBody
    @RequestMapping("/getVCode")
    public String getVCode(String phoneNum){
        loginSerivce.sendMs(phoneNum);
        return "{}";
    }

    /**
     * 验证前端发来的验证码,成功则关联密码创建用户
     * @url /login/sendVCode
     * @param phoneNum 用户手机号
     * @param vcode 收到的验证码
     * @param password 设置的密码
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
     * @url /login
     * @param phoneNum 用户手机号
     * @param password 密码
     * @return 成功:{"userID":"5a213059-d1a3-48c2-b0f5-ff16637f9a3a","phoneNum":"18272795103","generalPoints":0,"availablePoints":0,"rewardLinkCode":""}
     *          失败：{}
     */
    @ResponseBody
    @RequestMapping("")
    public String Login(String phoneNum,String password){
        User user  = loginSerivce.login(phoneNum,password);
        if(user==null){
            return "{}";
        }
        else{
            return gson.toJson(user);
        }

    }


}
