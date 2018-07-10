package citi.mybatismapper;


import citi.dao.UserDAO;
import citi.vo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    final String getInfoByPhoneNum = "SELECT userID, citiCardID, phoneNum, generalPoints, availablePoints, rewardLinkCode FROM user WHERE phoneNum = #{phoneNum}";
    final String getInfoByUserID = "SELECT userID, citiCardID, phoneNum, generalPoints, availablePoints, rewardLinkCode FROM user WHERE userID = #{userID}";
    final String loginVerify = "SELECT userID, citiCardID, phoneNum, generalPoints, availablePoints, rewardLinkCode FROM user WHERE phoneNum = #{phoneNum} AND password = #{password}";
    final String insertUser = "INSERT INTO user (userID, password, citiCardID, phoneNum, generalPoints, availablePoints, rewardLinkCode) " +
            "VALUES (#{userID}, #{password}, #{citiCardID}, #{phoneNum}, #{generalPoints}, #{availablePoints})";
    final String bindCitiCard = "UPDATE user SET CitiCardID = #{CitiCardID} WHERE userID = #{userID}";
    final String addLinkCode = "UPDATE user SET rewardLinkCode = #{rewardLinkCode} WHERE userID = #{userID}";

    @Select(getInfoByPhoneNum)
    User getInfoByPhone(String phoneNum);

    @Select(getInfoByUserID)
    User getInfoByUserID(String userID);

    //注解部分，登陆验证
    @Select(loginVerify)
    User select(@Param("phoneNum") String phoneNum, @Param("password") String password);

    //int为受影响的行数，插入成功为1，用来判断是否操作成功
    //If the BATCH executor is in use, the insert counts are being lost.
    @Insert(insertUser)
    int insert(UserDAO userDAO);

    @Update(bindCitiCard)
    int bindCitiCard(@Param("userID") String userID, @Param("CitiCardID") String CitiCardID);

    @Update(addLinkCode)
    int addLinkCode(@Param("userID") String userID, @Param("rewardLinkCode") String rewardLinkCode);

}
