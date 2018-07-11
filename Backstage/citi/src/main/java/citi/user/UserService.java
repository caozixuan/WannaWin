package citi.user;

import citi.mapper.UserMapper;
import citi.vo.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhong on 2018/7/11 19:23
 */
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getInfo(String userID){
        return userMapper.getInfoByUserID(userID);
    }
}
