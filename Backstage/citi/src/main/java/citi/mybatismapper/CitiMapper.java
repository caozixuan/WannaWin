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

    final String insertCiti = "INSERT INTO citicard(citiCard, phoneNum, ID, userID, password) " +
            "VALUES(#{citiCardNum}, #{phoneNum}, #{ID}, #{userID}, #{password})";
    final String deleteCiti = "DELETE FROM citicard WHERE citiCard = #{citiNum}";

    @Insert(insertCiti)
    int insert(CitiCard citiCard);

    @Delete(deleteCiti)
    int delete(String citiNum);

}
