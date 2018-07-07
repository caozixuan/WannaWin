package citi.dao;

import citi.vo.MSCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MSCardMapper {

    final String insert = "INSERT INTO MSCard (userID, cardID, merchantID, cardNo, points, proportion) VALUES (#{userID}, #{cardID}, #{merchantID}, #{cardNo}, #{points}, #{proportion})";
    final String getAll = "SELECT * FROM MSCard";
    final String getByuserId = "SELECT * FROM MSCard WHERE userID = #{userid}";
    final String deleteById = "DELETE from MSCard WHERE userID = #{userid}";

    //插入一个会员卡
    // 曹子轩：疑问这里int是怎么规定的？
    @Insert(insert)
    int insert(MSCard msCard);

    //查询用户下的会员卡
    //任思远：一个用户不是一张卡吗？
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
    MSCard selectCard(String cardID);
}
