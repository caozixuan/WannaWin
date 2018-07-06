package citi.dao;

import citi.vo.MSCard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MSCardMapper {

    //插入一个会员卡
    int insert(MSCard msCard);

    //查询用户下的会员卡
    List<MSCard> select(String userID);
}
