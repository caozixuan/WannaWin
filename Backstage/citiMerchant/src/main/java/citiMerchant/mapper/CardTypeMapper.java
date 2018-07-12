/**********      Deprecated      **********
 * 2018-07-11 16:07
 * 每个商户只允许发行一种卡，
 * 消费策略和商户绑定。

package citi.mybatismapper;

import citi.vo.CitiCard;
import citi.vo.MSCardType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardTypeMapper {

    final String getCardTypeByMerchantID = "SEECT * FROM cardtype WHERE MerchantID = #{merchantID}";
    final String getCardTypeInfo = "SELECT * FROM cardtype WHERE CardTypeID = #{CardTypeID}";
    final String addCardType = "INSERT INTO cardtype(MerchantID, MType, CardTypeID, Proportion, miniExpense) " +
            "VALUES(#{MerchantID}, #{MType}, #{CardTypeID}, #{Proportion}, #{miniExpense})";
    final String deleteCardType = "DELETE FROM cardtype WHERE CardTypeID = #{CardTypeID}";

    @Select(getCardTypeByMerchantID)
    List<MSCardType> getCardTypeByMerchantID(String merchantID);

    @Select(getCardTypeInfo)
    MSCardType getCardTypeInfo(String CardTypeID);

    @Insert(addCardType)
    int addCardType(MSCardType msCardType);

    @Delete(deleteCardType)
    int deleteCardType(String CardType);

}
 */
