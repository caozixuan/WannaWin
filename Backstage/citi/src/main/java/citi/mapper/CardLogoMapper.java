package citi.mapper;

import citi.vo.CardLogo;
import citi.vo.CitiCard;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CardLogoMapper {

    final String getLogoURLByMerchantID = "SELECT * FROM cardLogo WHERE MerchantID = #{merchantID}";
    final String addCardLogo = "INSERT INTO cardLogo(MerchantID, logoURL) " +
            "VALUES(#{merchantID}, #{logoURL})";
    final String updateCardLogo = "UPDATE cardLogo " +
            "SET logoURL = #{logoURL} WHERE MerchantID = #{merchantID}";
    final String deleteCardLogo = "DELETE FROM cardLogo WHERE MerchantID = #{merchantID}";

    @Select(getLogoURLByMerchantID)
    String getLogoURLByMerchantID(String merchantID);

    @Insert(addCardLogo)
    int addCardLogo(CardLogo cardLogo);

    @Update(updateCardLogo)
    int updateCardLogo(CardLogo cardLogo);

    @Delete(deleteCardLogo)
    int deleteCardLogo(String merchantID);

}
