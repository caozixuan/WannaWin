package citi.persist.mapper;

import citi.vo.Points_history;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointsHistoryMapper {

    final String getPointsHistoryByID = "SELECT * FROM points_history WHERE userID = #{userID} ORDER BY time DESC";
    final String getPointsHistoryBy_userID_AND_merchantID = "SELECT * FROM points_history WHERE userID = #{userID} AND merchantID = #{merchantID} ORDER BY time DESC";

    @Select(getPointsHistoryByID)
    List<Points_history> getPointsHistoryByID(String userID);

    @Select(getPointsHistoryBy_userID_AND_merchantID)
    List<Points_history> getPointsHistoryBy_userID_AND_merchantID(@Param("userID") String userID, @Param("merchantID") String merchantID);

}
