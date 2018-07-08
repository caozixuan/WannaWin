package citi.dao;

import citi.vo.CitiCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface CitiMapper {

    final String insertCiti = "INSERT INTO citicard VALUES(#{citiCardNum}, #{phoneNum}, #{ID}, #{password}) ";
    final String deleteCiti = "";



    @Insert(insertCiti)
    int insert(CitiCard citiCard);

    int delete(String citiNum);
}
