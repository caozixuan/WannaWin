package citi.mapper;

import citi.vo.MSCard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MSCardMapper {

    final String insert = "INSERT INTO m_card (userID, cardNum, points, MerchantID) VALUES (#{userID}, #{cardNum}, #{points}, #{merchantId})";
    final String getByuserId = "SELECT * FROM m_card WHERE userID = #{userID}";
    final String getBy_userID_AND_merchantID = "SELECT * FROM m_card WHERE UserID = #{userID} AND MerchantID = #{merchantID}";

    //插入一个会员卡d
    @Insert(insert)
    int insert(MSCard msCard);

    //查询用户下的会员卡
    @Select(getByuserId)
    List<MSCard> select(String userID);

    @Select(getBy_userID_AND_merchantID)
    MSCard getBy_userID_AND_merchantID(@Param("userID") String userID, @Param("merchantID") String merchantID);

}
