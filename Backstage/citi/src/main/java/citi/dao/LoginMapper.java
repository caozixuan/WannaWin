package citi.dao;


import citi.vo.UserInfo;
import citi.vo.VCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface LoginMapper {

    final String insertVCode = "INSERT INTO Vcode(phoneNum, VCode Time) VALUES(#{phoneNum}, #{VCode}, #{Time})";
    final String getByPhoneNum = "SELECT VCode FROM Vcode " +
            "WHERE phoneNum = #{phoneNum} AND timediff(now(), Time) ";

    //注解，添加向前端发送的验证码至数据库
    @Insert(insertVCode)
    int insertVcode(VCode vcode);

    //从数据库中搜索对应的验证码
    @Select(getByPhoneNum)
    String selectVcode(String phoneNum);

}
