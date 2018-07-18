package citiMerchant.mapper;

import citiMerchant.vo.MSCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MSCardMapper {

    final String insert = "INSERT INTO m_card (userID, cardNum, points, MerchantID) VALUES (#{userID}, #{cardNum}, #{points}, #{merchantId})";
    final String getByuserId = "SELECT * FROM m_card WHERE userID = #{userID}";
    final String getBy_userID_AND_merchantID = "SELECT * FROM m_card WHERE UserID = #{userID} AND MerchantID = #{merchantID}";
    final String grantPoints = "UPDATE m_card SET Points = Points + #{points} WHERE UserID = #{userID} AND MerchantID = #{merchantID}";
    final String exchangePoints = "UPDATE m_card SET Points = Points - #{points} WHERE UserID = #{userID} AND MerchantID = #{merchantID}";
    final String unBind = "DELETE FROM m_card WHERE UserID = #{userID} AND MerchantID = #{merchantID}";

    //插入一个会员卡d
    @Insert(insert)
    int insert(MSCard msCard);

    //查询用户下的会员卡
    @Select(getByuserId)
    List<MSCard> select(String userID);

    @Select(getBy_userID_AND_merchantID)
    MSCard getBy_userID_AND_merchantID(@Param("userID") String userID, @Param("merchantID") String merchantID);

    @Update(grantPoints)
    int grantPoints(@Param("points") int points, @Param("userID") String userID, @Param("merchantID") String merchantID);

    @Update(exchangePoints)
    int exchangePoints(@Param("points") int points, @Param("userID") String userID, @Param("merchantID") String merchantID);

    @Delete(unBind)
    int unBind(@Param("userID") String userID, @Param("merchantID") String merchantID);

}
