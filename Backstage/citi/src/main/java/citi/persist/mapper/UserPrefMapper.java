package citi.persist.mapper;


import citi.vo.UserPref;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrefMapper {

    final String getUserPref = "SELECT * FROM userPref WHERE userID = #{userID}";
    final String addUserPref = "INSERT INTO userPref(userID, prefType) VALUES(#{userID}, #{prefType})";
    final String updateUserPref = "UPDATE userPref SET prefType = #{prefType} WHERE userID = #{userID}";
    final String deleteUserPref = "DELETE FROM userPref WHERE userID = #{userID}";


    @Select(getUserPref)
    UserPref getUserPref(String userID);

    @Insert(addUserPref)
    int addUserPref(UserPref userPref);

    @Update(updateUserPref)
    int updateUserPref(@Param("userID") String userID, @Param("prefType") String prefType);

    @Delete(deleteUserPref)
    int deleteUserPref(String userID);

}




