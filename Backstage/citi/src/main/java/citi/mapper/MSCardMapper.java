package citi.mapper;

import citi.vo.MSCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MSCardMapper {

    final String insert = "INSERT INTO m_card (cardID, userID, cardNum, points, MerchantID) VALUES (#{cardID}, #{userID}, #{cardNum}, #{points}, #{merchantId})";
    final String getByuserId = "SELECT * FROM m_card WHERE userID = #{userID}";
    final String getCard = "SELECT * FROM m_card WHERE cardID = #{cardID}";

    //插入一个会员卡d
    @Insert(insert)
    int insert(MSCard msCard);

    //查询用户下的会员卡
    @Select(getByuserId)
    List<MSCard> select(String userID);

    @Select(getCard)
    MSCard selectCard(String cardID);

}
