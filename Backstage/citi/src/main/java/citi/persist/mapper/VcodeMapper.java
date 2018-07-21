package citi.persist.mapper;


import citi.vo.VCode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface VcodeMapper {

    final String intervalTime = "3000";  //30分钟之内的验证码
    final String insertVCode = "INSERT INTO Vcode (phoneNum, VCode, Time) VALUES (#{phoneNum}, #{VCode}, #{Time})";
    final String getByPhoneNum = "SELECT VCode FROM Vcode WHERE phoneNum = #{phoneNum} AND Time >= now() - " + intervalTime + " AND Time <= now()";
    final String deleteByPhoneNum = "DELETE FROM Vcode WHERE phoneNum = #{phoneNum}";

    //注解，添加向前端发送的验证码至数据库
    @Insert(insertVCode)
    int insertVcode(VCode vcode);

    //从数据库中搜索对应的验证码
    @Select(getByPhoneNum)
    List<String> selectVcode(String phoneNum);

    //从数据库中删除已经完成注册的用户的验证码
    @Delete(deleteByPhoneNum)
    int deleteVcode(String phoneNum);

}
