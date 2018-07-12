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
    final String addStrategy = "INSERT INTO Strategy(strategyID, MerchantID, full, discount, points) " +
            "VALUES(#{strategyID}, #{MerchantID}, #{full}, #{CardTypeID}, #{discount}, #{points})";
    final String updateStrategy = "UPDATE strategy SET full = #{full}, discount = #{discount}, points = #{points} " +
            "WHERE strategyID = #{strategyID}";
    final String deleteStrategy = "DELETE FROM Strategy WHERE MerchantID = #{merchantID}";

    @Select(getStrategysByMerchantID)
    List<StrategyDAO> getStrategysByMerchantID(String merchantID);

    @Insert(addStrategy)
    int addStrategy(StrategyDAO strategyDAO);

    @Update(updateStrategy)
    int updateStrategy(StrategyDAO strategyDAO);

    @Delete(deleteStrategy)
    int deleteStrategy(String merchantID);

    final String getStrategyAmountByMerchantID = "SELECT COUNT(*) FROM strategy WHERE MerchantID = #{merchantID}";

    @Select(getStrategyAmountByMerchantID)
    int getStrategyAmountByMerchantID(String merchantID);

}
