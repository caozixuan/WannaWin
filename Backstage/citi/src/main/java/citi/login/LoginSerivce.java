package citi.login;

import citi.API.AliSMS;
import citi.mapper.VcodeMapper;
import citi.mapper.UserMapper;
import citi.vo.User;
import citi.vo.VCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 接口设计：刘钟博
 * 代码填充：彭璇
 */
@Service
public class LoginSerivce {

    @Autowired
    private VcodeMapper vcodeMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 发送验证码，储存验证码至数据库中
     * @param phoneNum 手机号
     */
    public void sendMs(String phoneNum){
        try{
            String vcode = AliSMS.sendSms(phoneNum);
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            VCode v = new VCode(phoneNum,vcode,timestamp.toString());
            vcodeMapper.insertVcode(v);
        }
        catch (Exception e){
            e.printStackTrace();
            //之后做日志记录，手机号不合格等...
        }
    }

    /**
     * 验证验证码
     * @param phoneNum 手机号
     * @param vCode 验证码
     * @return 是否匹配成功
     */
    public boolean vfVcode(String phoneNum,String vCode,String password){
        boolean isMatch = false;
        List<String> vCodes = vcodeMapper.selectVcode(phoneNum);
        for(int i=0;i<vCodes.size();i++){
            if(vCodes.get(i).equals(vCode)){
                isMatch = true;
                User d = new User(phoneNum,password);
                userMapper.insert(d);
                vcodeMapper.deleteVcode(phoneNum);
                break;
            }
        }
        return isMatch;
    }

    /**
     * 验证登陆
     * @param phoneNum 手机号
     * @param password 密码
     * @return user
     *
     */
    public User login(String phoneNum, String password){
        return userMapper.select(phoneNum,password);
    }
}
