package citi.persist.mapper;

import citi.vo.Strategy;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrategyMapper {

    final String getStrategysByMerchantID = "SELECT * FROM Strategy WHERE MerchantID = #{merchantID} ORDER BY full DESC";
    final String getStrategyIDByMerchantID = "SELECT strategyID FROM Strategy WHERE MerchantID = #{MerchantID}";
    final String getStrategyByStrategyID = "SELECT * FROM Strategy WHERE strategyID = #{strategyID}";
    final String addStrategy = "INSERT INTO Strategy(strategyID, MerchantID, full, priceAfter, points) " +
            "VALUES(#{strategyID}, #{MerchantID}, #{full}, #{priceAfter}, #{points})";
    final String updateStrategy = "UPDATE Strategy SET full = #{full}, priceAfter = #{priceAfter}, points = #{points} " +
            "WHERE strategyID = #{strategyID}";
    final String deleteStrategy = "DELETE FROM Strategy WHERE MerchantID = #{merchantID}";

    @Select(getStrategysByMerchantID)
    List<Strategy> getStrategysByMerchantID(String merchantID);


    @Select(getStrategyIDByMerchantID)
    List<String> getStrategyIDByMerchantID(String MerchantID);

    @Select(getStrategyByStrategyID)
    Strategy getStrategyByStrategyID(String strategyID);

    @Insert(addStrategy)
    int addStrategy(Strategy strategy);

    @Update(updateStrategy)
    int updateStrategy(Strategy strategy);

    @Delete(deleteStrategy)
    int deleteStrategy(String merchantID);

    final String getStrategyAmountByMerchantID = "SELECT COUNT(*) FROM Strategy WHERE MerchantID = #{merchantID}";

    @Select(getStrategyAmountByMerchantID)
    int getStrategyAmountByMerchantID(String merchantID);

}
