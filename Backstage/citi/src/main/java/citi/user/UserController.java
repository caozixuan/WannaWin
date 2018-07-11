package citi.user;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhong on 2018/7/11 19:20
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;

    @RequestMapping("/getInfo")
    @ResponseBody
    public String getInfo(String userID){
        return  gson.toJson(userService.getInfo(userID));
    }
}
