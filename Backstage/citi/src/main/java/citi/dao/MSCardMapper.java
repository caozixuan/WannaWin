package citi.dao;

import citi.vo.MSCard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MSCardMapper {

    //插入一个会员卡
    // 曹子轩：疑问这里int是怎么规定的？
    int insert(MSCard msCard);

    //查询用户下的会员卡
    List<MSCard> select(String userID);

    // 曹子轩：疑问：这里是不是缺了一个根据卡号选卡的操作？
    MSCard selectCard(String cardID);
}
