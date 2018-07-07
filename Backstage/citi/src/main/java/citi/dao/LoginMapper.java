package citi.dao;


import org.springframework.stereotype.Repository;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface LoginMapper {

    //注解，添加向前端发送的验证码至数据库
    int insertVcode(String phoneNum, String vcode);

    //从数据库中搜索对应的验证码
    String selectVcode(String phoneNum);

}
