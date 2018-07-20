package citiMerchant.mapper;

import citiMerchant.vo.StrategyDAO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrategyMapper {

    final String getStrategysByMerchantID = "SELECT * FROM Strategy WHERE MerchantID = #{merchantID} ORDER BY full DESC";
    final String getStrategyIDByMerchantID = "SELECT strategyID FROM Strategy WHERE MerchantID = #{MerchantID}";
    final String getStrategyByStrategyID = "SELECT * FROM Strategy WHERE strategyID = #{strategyID}";
    final String addStrategy = "INSERT INTO Strategy(strategyID, MerchantID, full, priceAfter, points) " +
            "VALUES(#{strategyID}, #{merchantID}, #{full}, #{priceAfter}, #{points})";
    final String updateStrategy = "UPDATE Strategy SET full = #{full}, priceAfter = #{priceAfter}, points = #{points} " +
            "WHERE strategyID = #{strategyID}";
    final String deleteStrategy = "DELETE FROM Strategy WHERE strategyID = #{strategyID}";

    @Select(getStrategysByMerchantID)
    List<StrategyDAO> getStrategysByMerchantID(String merchantID);

    @Select(getStrategyIDByMerchantID)
    List<String> getStrategyIDByMerchantID(String MerchantID);

    @Select(getStrategyByStrategyID)
    StrategyDAO getStrategyByStrategyID(String strategyID);

    @Insert(addStrategy)
    int addStrategy(StrategyDAO strategyDAO);

    @Update(updateStrategy)
    int updateStrategy(@Param("strategyID") String strategyID, @Param("full") Double full,@Param("priceAfter") Double priceAfter,@Param("points") Double points);

    @Delete(deleteStrategy)
    int deleteStrategy(String strategyID);

    final String getStrategyAmountByMerchantID = "SELECT COUNT(*) FROM Strategy WHERE MerchantID = #{merchantID}";

    @Select(getStrategyAmountByMerchantID)
    int getStrategyAmountByMerchantID(String merchantID);

}
