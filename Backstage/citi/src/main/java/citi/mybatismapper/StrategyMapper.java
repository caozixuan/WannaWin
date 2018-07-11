package citi.mybatismapper;


import citi.dao.StrategyDAO;
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
    List<StrategyDAO> getStrategysByMerchantID(String merchantID);

    @Select(getStrategy)
    StrategyDAO getStrategy(String CardTypeID);

    @Insert(addStrategy)
    int addStrategy(StrategyDAO strategy);

    @Delete(deleteStrategy)
    int deleteStrategy(String CardTypeID);

}
