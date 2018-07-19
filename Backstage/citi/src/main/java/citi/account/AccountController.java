package citi.account;

import citi.support.resultjson.ResultJson;
import citi.support.resultjson.SerializeGson;
import citi.user.UserService;
import citi.vo.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.regex.*;


/**
 * 接口设计：刘钟博
 * 代码填充：彭璇
 * bug修复：刘钟博
 */
@RequestMapping(value = {"/account"},produces = {"text/html;charset=UTF-8"})
@Controller
public class AccountController {

    @Autowired
    private Gson gson;
    @Autowired
    private AccountSerivce accountSerivce;
    @Autowired
    private UserService userService;

    /**
     * 前端请求发送验证码
     * @url /account/getVCode
     * @param phoneNum 请求手机号
     *
     */
    @ResponseBody
    @RequestMapping("/getVCode")
    public String getVCode(String phoneNum){
        String pattern = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\\\d{8}$";
        boolean isMatch = Pattern.matches(pattern,phoneNum);
        if(!isMatch){
            return ResultJson.FAILURE;
        }
        if (accountSerivce.sendMs(phoneNum)){
            return ResultJson.SUCCESS;
        }else {
            return ResultJson.FAILURE;
        }

    }

    /**
     * 验证前端发来的验证码,成功则关联密码创建用户
     * @url /account/sendVCode
     * @param phoneNum 用户手机号
     * @param vcode 收到的验证码
     * @param password 设置的密码
     * @return 成功：{"isCreate": true｝
     *          失败：{"isCreate": false｝
     */
    @ResponseBody
    @RequestMapping("/sendVCode")
    public String sendVcode(String phoneNum,String vcode,String password){
        boolean isMatch = accountSerivce.vfCodeAndCreate(phoneNum,vcode,password);
        if (isMatch){
            return ResultJson.SUCCESS;
        }
        return ResultJson.FAILURE;
    }

    /**
     * 验证登陆
     * @url /account
     * @param phoneNum 用户手机号
     * @param password 密码
     * @return 成功:{"userID":"5a213059-d1a3-48c2-b0f5-ff16637f9a3a","phoneNum":"18272795103","generalPoints":0,"availablePoints":0,"rewardLinkCode":""}
     *          失败：{}
     */
    @ResponseBody
    @RequestMapping("/login")
    public String Login(String phoneNum,String password){
        User user  = accountSerivce.login(phoneNum,password);
        if(user==null){
            return "{}";
        }
        else{
            user.setAvailablePoints(userService.getAvailablePoints(user));
            return SerializeGson.GSON.toJson(user);
        }
    }

    /**
     * 忘记密码，更新密码
     * @param phoneNum
     * @param newPassword
     * @return
     */
    @ResponseBody
    @RequestMapping("/resetPassword")
    public String resetPassword(String phoneNum,String newPassword){
        if (accountSerivce.resetPassword(phoneNum,newPassword)){
            return ResultJson.SUCCESS;
        }else {
            return ResultJson.FAILURE;
        }

    }

    @ResponseBody
    @RequestMapping("/vfcode")
    public String vfVcode(String phoneNum,String vcode){
        if (accountSerivce.vfVcode(phoneNum,vcode)){
            return ResultJson.SUCCESS;
        }else {
            return ResultJson.FAILURE;
        }
    }

    @ResponseBody
    @RequestMapping("/changePassword")
    public String changePassword(String userID,String oldPassword,String newPassword){
        if (accountSerivce.changePassword(userID,oldPassword,newPassword)){
            return ResultJson.SUCCESS;
        }else {
            return ResultJson.FAILURE;
        }
    }
}
