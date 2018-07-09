package citi.mybatismapper;


import citi.vo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    final String loginVerify = "SELECT userID, phoneNum, generalPoints, availablePoints, citiCardNum FROM user WHERE phoneNum = #{phoneNum} AND password = #{password}";
    final String insertUser = "INSERT INTO user (userID, password, phoneNum, generalPoints, availablePoints, citiCardNum) " +
            "VALUES (#{userID}, #{password}, #{phoneNum}, 0, 0, null)";


    //注解部分，登陆验证
    @Select(loginVerify)
    @Results(
            value = {
                    @Result(property = "userID", column = "userID"),
                    @Result(property = "phoneNum", column = "phoneNum"),
                    @Result(property = "generalPoints", column = "generalPoints"),
                    @Result(property = "availablePoints", column = "availablePoints"),
                    @Result(property = "citiCardNum", column = "citiCardNum")
            }
    )
    User select(String phoneNum, String password);

    //int为受影响的行数，插入成功为1，用来判断是否操作成功
    //If the BATCH executor is in use, the insert counts are being lost.
    @Insert(insertUser)
    int insert(@Param("userID") String userID,@Param("phoneNum") String phoneNum,@Param("password") String password);
}
