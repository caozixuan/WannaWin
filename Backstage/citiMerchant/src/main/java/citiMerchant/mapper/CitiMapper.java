package citiMerchant.mapper;

import citiMerchant.vo.CitiCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiMapper {

    final String getCardByID = "SELECT * FROM citicard WHERE userID = #{userID}";
    final String insertCiti = "INSERT INTO citicard(citiCardID, citiCardNum, phoneNum, userID, miniExpense) " +
            "VALUES(#{citiCardID}, #{citiCardNum}, #{phoneNum}, #{userID}, #{miniExpense})";
    final String updateCiti = "UPDATE citicard " +
            "SET citiCardID = #{citiCardID}, citiCardNum = #{citiCardNum}, phoneNum = #{phoneNum}, miniExpense = #{miniExpense}" +
            "WHERE userID = #{userID}";
    final String deleteCiti = "DELETE FROM citicard WHERE citiCardID = #{citiCardID}";

    @Select(getCardByID)
    CitiCard getCardByID(String userID);

    @Insert(insertCiti)
    int insert(CitiCard citiCard);

    @Update(updateCiti)
    int update(CitiCard citiCard);

    @Delete(deleteCiti)
    int delete(String citiNum);

}
