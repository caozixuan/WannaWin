package citi.dao;


import citi.vo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    //注解部分，登陆验证
    User select(String username, String password);

    //int为受影响的行数，插入成功为1，用来判断是否操作成功
    int insert(User user);
}
