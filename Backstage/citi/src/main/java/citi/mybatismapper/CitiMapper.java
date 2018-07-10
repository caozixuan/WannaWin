package citi.mybatismapper;

import citi.vo.CitiCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface CitiMapper {

    final String insertCiti = "INSERT INTO citicard(citiCard, phoneNum, userID) " +
            "VALUES(#{citiCardNum}, #{phoneNum}, #{userID})";
    final String updateCitiByPhoneNum = "UPDATE citicard SET citiCard = #{citiCardNum} WHERE phoneNum = #{phoneNum}";
    final String updateCitiByID = "UPDATE citicard SET citiCard = #{citiCardNum} WHERE userID = #{userID}";
    final String deleteCiti = "DELETE FROM citicard WHERE citiCard = #{citiNum}";

    @Insert(insertCiti)
    int insert(CitiCard citiCard);

    @Update(updateCitiByPhoneNum)
    int updateByPhoneNum(@Param("phoneNum") String phoneNum, @Param("citiCard") String citiCardNum);

    @Update(updateCitiByID)
    int updateByUserID(@Param("userID") String userID, @Param("citiCard") String citiCardNum);

    @Delete(deleteCiti)
    int delete(String citiNum);

}
