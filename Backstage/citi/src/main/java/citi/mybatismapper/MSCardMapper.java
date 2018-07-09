package citi.mybatismapper;

import citi.dao.MSCardDAO;
import citi.vo.MSCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MSCardMapper {

    final String insert = "INSERT INTO m_card (userID, cardID, merchantID, cardNo, points, proportion) VALUES (#{userID}, #{cardID}, #{merchantID}, #{cardNo}, #{points}, #{proportion})";
    final String getByuserId = "SELECT * FROM m_card WHERE userID = #{userid}";
    final String getCard = "SELECT * FROM m_card WHERE cardID = #{cardID}";

    //插入一个会员卡
    @Insert(insert)
    int insert(MSCard msCard);

    //查询用户下的会员卡
    @Select(getByuserId)
    @Results(
            value = {
                    @Result(property = "userID", column = "userID"),
                    @Result(property = "cardID", column = "cardID"),
                    @Result(property = "merchantID", column = "merchantID"),
                    @Result(property = "cardNo", column = "cardNo"),
                    @Result(property = "points", column = "points"),
                    @Result(property = "proportion", column = "proportion")
            }
    )
    List<MSCard> select(String userID);

    // 曹子轩：疑问：这里是不是缺了一个根据卡号选卡的操作？
    @Select(getCard)
    @Results(
            value = {
                    @Result(property = "cardID", column = "cardID"),
                    @Result(property = "UserID", column = "UserID"),
                    @Result(property = "cardNo", column = "cardNo"),
                    @Result(property = "points", column = "points"),
                    @Result(property = "cardType", column = "CardType")
            }
    )
    MSCardDAO selectCard(String cardID);
}
