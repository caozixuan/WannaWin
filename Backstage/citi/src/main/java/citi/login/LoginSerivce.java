package citi.login;

import citi.dao.LoginMapper;
import citi.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginSerivce {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 发送验证码，储存验证码至数据库中
     * @param phoneNum
     */
    public void sendMs(String phoneNum){

    }

    /**
     * 验证验证码
     * @param userId
     * @param vCode
     */
    public void vfVcode(String userId,String vCode,String password){

    }

    /**
     * 验证登陆
     * @param userName
     * @param password
     * @return
     */
    public boolean login(String userName,String password){

        return false;
    }
}
