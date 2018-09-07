package citi.funcModule.account;

import citi.API.AliSMS;
import citi.persist.mapper.VcodeMapper;
import citi.persist.mapper.UserMapper;
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
public class AccountSerivce {

    @Autowired
    private VcodeMapper vcodeMapper;

    @Autowired
    private UserMapper userMapper;



    /**
     * 发送验证码，储存验证码至数据库中
     * @param phoneNum 手机号
     */
    public boolean sendMs(String phoneNum){
        try{
            String vcode = AliSMS.sendSms(phoneNum);
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            VCode v = new VCode(phoneNum,vcode,timestamp);
            vcodeMapper.insertVcode(v);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            //TODO:异常日志记录
            return false;
        }
    }

    /**
     * 验证验证码
     * @param phoneNum 手机号
     * @param vCode 验证码
     * @return 是否匹配成功
     */
    public boolean vfVcode(String phoneNum,String vCode){
        try{
            List<String> vCodes = vcodeMapper.selectVcode(phoneNum);
            for(int i=0;i<vCodes.size();i++){
                if(vCodes.get(i).equals(vCode)){
                    vcodeMapper.deleteVcode(phoneNum);
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            //TODO:异常日志记录
            return false;
        }
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

    public boolean createUser(String phoneNum,String password){
        try{
            User d = new User(phoneNum,password);
            userMapper.insert(d);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean vfCodeAndCreate(String phoneNum,String vCode,String password){
        if (vfVcode(phoneNum,vCode)){
            return createUser(phoneNum,password);
        }else {
            return false;
        }

    }

    public boolean resetPassword(String phoneNum,String password){
        User user=userMapper.getInfoByPhone(phoneNum);
        if (user==null){
            return createUser(phoneNum,password);
        }
        if (userMapper.changePasswordByPhoneNum(phoneNum,password)==1){
            return true;
        }
        return false;
    }

    public boolean changePassword(String userID,String oldPassword,String newPassword){
        User user=userMapper.getInfoByUserID(userID);
        if (user==null){
            return false;
        }
        if (user.getPassword().equals(oldPassword)){
            if (userMapper.changePasswdByUserID(userID,newPassword)==1){
                return true;
            }
            return false;
        }
        return false;
    }
}
