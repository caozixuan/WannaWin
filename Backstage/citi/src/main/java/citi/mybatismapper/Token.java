package citi.mybatismapper;

import citi.vo.CitiCard;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface Token {

    final String selectTokenByID = "SELECT * FROM user_token WHERE userID = #{userID}";
    final String insertToken = "INSERT INTO user_token() VALUES(#{}, #{}, #{}, #{})";
    final String updateToken = "UPDATE user_token SET";
    final String deleteToken = "DELETE FROM user_token WHERE userID = #{userID}";

    @Select(selectTokenByID)
    String select(String userID);

    @Insert(insertToken)
    int insert(Token citiCard);

    @Update(updateToken)
    int update();


    @Delete(deleteToken)
    int delete(String userID);

}
