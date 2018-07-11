package citi.mybatismapper;


import citi.dao.StrategyDAO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StrategyMapper {

    final String getStrategysByMerchantID = "SELECT * FROM Strategy WHERE MerchantID = #{merchantID}";
    final String addStrategy = "INSERT INTO Strategy(CardTypeID, full, discount, points) " +
            "VALUES(#{CardTypeID}, #{full}, #{CardTypeID}, #{discount}, #{points})";
    final String deleteStrategy = "DELETE FROM Strategy WHERE MerchantID = #{merchantID}";

    @Select(getStrategysByMerchantID)
    List<StrategyDAO> getStrategysByMerchantID(String merchantID);

    @Insert(addStrategy)
    int addStrategy(StrategyDAO strategy);

    @Delete(deleteStrategy)
    int deleteStrategy(String merchantID);

}
