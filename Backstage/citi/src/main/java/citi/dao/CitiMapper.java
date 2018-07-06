package citi.dao;

import citi.vo.CitiCard;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiMapper {

    int insert(CitiCard citiCard);

    int delete(String citiNum);
}
