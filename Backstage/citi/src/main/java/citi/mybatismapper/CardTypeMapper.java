package citi.mybatismapper;

import citi.vo.CitiCard;
import citi.vo.MSCardType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CardTypeMapper {

    final String getCardTypeInfo = "SELECT * FROM cardtype WHERE CardType = #{CardType}" +
            "VALUES(#{citiCardID}, #{citiCardNum}, #{phoneNum}, #{userID})";
    final String addCardType = "INSERT INTO cardtype(MerchantID, MType, CardType, Proportion, miniExpense) " +
            "VALUES(#{MerchantID}, #{MType}, #{CardType}, #{Proportion}, #{miniExpense})";
    final String deleteCardType = "DELETE FROM cardtype WHERE CardType = #{CardType}";

    @Select(getCardTypeInfo)
    MSCardType getCardTypeInfo(String CardType);

    @Insert(addCardType)
    int addCardType(MSCardType msCardType);

    @Delete(deleteCardType)
    int deleteCardType(String CardType);

}
