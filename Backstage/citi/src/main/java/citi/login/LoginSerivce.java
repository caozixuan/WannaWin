package citi.login;

import citi.API.AliSMS;
import citi.API.VerificationCode;
import citi.dao.UserDAO;
import citi.mybatismapper.LoginMapper;
import citi.mybatismapper.UserMapper;
import citi.vo.User;
import citi.vo.VCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 接口设计：刘钟博
 * 代码填充：彭璇
 */
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
        try{
            String vcode = AliSMS.sendSms(phoneNum);
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
           // VCode v = new VCode(phoneNum,vcode,timestamp.toString());
            VCode v = new VCode(phoneNum,vcode,timestamp.toString());
            loginMapper.insertVcode(v);
        }
        catch (Exception e){

        }
    }

    /**
     * 验证验证码
     * @param phoneNum
     * @param vCode
     */
    public boolean vfVcode(String phoneNum,String vCode,String password){
        boolean isMatch = false;
        List<String> vCodes = loginMapper.selectVcode(phoneNum);
        for(int i=0;i<vCodes.size();i++){
            if(vCodes.get(i).equals(vCode)){
                isMatch = true;
                UserDAO d = new UserDAO(UUID.randomUUID().toString().toLowerCase(),password,null,phoneNum,0,0);
                userMapper.insert(d);
                loginMapper.deleteVcode(phoneNum);
                break;
                //userMapper.insert(UUID.randomUUID().toString().toLowerCase(),phoneNum,password,null,);
            }
        }
        return isMatch;
    }

    /**
     * 验证登陆
     * @param phoneNum
     * @param password
     * @return boolean isSucceed
     *
     */
    public User login(String phoneNum, String password){
        return userMapper.select(phoneNum,password);
    }
}
