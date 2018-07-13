package citiMerchant.mapper;

import citiMerchant.vo.StrategyDAO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrategyMapper {

    final String getStrategysByMerchantID = "SELECT * FROM Strategy WHERE MerchantID = #{merchantID}";
    final String getStrategyIDByMerchantID = "SELECT strategyID FROM Strategy WHERE MerchantID = #{MerchantID}";
    final String getStrategyByStrategyID = "SELECT * FROM Strategy WHERE strategyID = #{strategyID}";
    final String addStrategy = "INSERT INTO Strategy(strategyID, MerchantID, full, discount, points) " +
            "VALUES(#{strategyID}, #{MerchantID}, #{full}, #{CardTypeID}, #{discount}, #{points})";
    final String updateStrategy = "UPDATE Strategy SET full = #{full}, discount = #{discount}, points = #{points} " +
            "WHERE strategyID = #{strategyID}";
    final String deleteStrategy = "DELETE FROM Strategy WHERE MerchantID = #{strategyID}";

    @Select(getStrategysByMerchantID)
    List<StrategyDAO> getStrategysByMerchantID(String merchantID);

    @Select(getStrategyIDByMerchantID)
    List<String> getStrategyIDByMerchantID(String MerchantID);

    @Select(getStrategyByStrategyID)
    StrategyDAO getStrategyByStrategyID(String strategyID);

    @Insert(addStrategy)
    int addStrategy(StrategyDAO strategyDAO);

    @Update(updateStrategy)
    int updateStrategy(StrategyDAO strategyDAO);

    @Delete(deleteStrategy)
    int deleteStrategy(String strategyID);

    final String getStrategyAmountByMerchantID = "SELECT COUNT(*) FROM Strategy WHERE MerchantID = #{merchantID}";

    @Select(getStrategyAmountByMerchantID)
    int getStrategyAmountByMerchantID(String merchantID);

}
