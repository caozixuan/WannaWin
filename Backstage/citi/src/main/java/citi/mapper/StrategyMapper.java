package citi.mapper;


import citi.vo.Strategy;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrategyMapper {

    final String getStrategysByMerchantID = "SELECT CardTypeID, full, discount FROM Strategy NATURAL JOIN cardtype WHERE MerchantID = #{merchantID}";
    final String getStrategy = "SELECT * FROM Strategy WHERE CardTypeID = #{CardTypeID}";
    final String addStrategy = "INSERT INTO Strategy(CardTypeID, full, discount) " +
            "VALUES(#{CardTypeID}, #{full}, #{CardTypeID}, #{discount})";
    final String deleteStrategy = "DELETE FROM Strategy WHERE CardTypeID = #{CardTypeID}";

    @Select(getStrategysByMerchantID)
    List<Strategy> getStrategysByMerchantID(String merchantID);

    @Select(getStrategy)
    Strategy getStrategy(String CardTypeID);

    @Insert(addStrategy)
    int addStrategy(Strategy strategy);

    @Delete(deleteStrategy)
    int deleteStrategy(String CardTypeID);

}
