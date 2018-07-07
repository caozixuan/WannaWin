package citi.dao;

import citi.vo.CitiCard;
import org.springframework.stereotype.Repository;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface CitiMapper {

    int insert(CitiCard citiCard);

    int delete(String citiNum);
}
