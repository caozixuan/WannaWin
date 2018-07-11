package citi.mapper;

import citi.vo.CitiCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface CitiMapper {

    final String getCardByID = "SELECT * FROM citicard WHERE userID = #{userID}";
    final String insertCiti = "INSERT INTO citicard(citiCardID, citiCardNum, phoneNum, userID) " +
            "VALUES(#{citiCardID}, #{citiCardNum}, #{phoneNum}, #{userID})";
    final String deleteCiti = "DELETE FROM citicard WHERE citiCardID = #{citiCardID}";

    @Select(getCardByID)
    CitiCard getCardByID(String userID);

    @Insert(insertCiti)
    int insert(CitiCard citiCard);

    @Delete(deleteCiti)
    int delete(String citiNum);

}
