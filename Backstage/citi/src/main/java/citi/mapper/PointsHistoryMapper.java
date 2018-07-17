package citi.mapper;

import citi.vo.Points_history;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsHistoryMapper {

    final String getPointsHistoryByID = "SELECT * FROM points_history WHERE userID = #{userID}";
    final String getPointsHistoryBy_userID_AND_merchantID = "SELECT * FROM points_history WHERE userID = #{userID} AND merchantID = #{merchantID}";

    @Select(getPointsHistoryByID)
    Points_history getPointsHistoryByID(String userID);

    @Select(getPointsHistoryBy_userID_AND_merchantID)
    Points_history getPointsHistoryBy_userID_AND_merchantID(@Param("userID") String userID, @Param("merchantID") String merchantID);

}
