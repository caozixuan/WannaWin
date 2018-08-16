package citi.persist.mapper;


import citi.vo.Activity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityMapper {

    final String getActivityByActivityID = "SELECT * FROM activity WHERE activityID = #{activityID}";
    final String getActivitiesByMerchantID = "SELECT * FROM activity WHERE merchantID = #{merchantID}";
    final String updateActivity = "UPDATE activity SET name=#{name}, description=#{description}, startDate=#{startDate}, endDate=#{endDate}, imageURL=#{imageURL} WHERE activityID=#{activityID}";
    final String insertActivity = "INSERT INTO activity (activityID, merchantID, name, description, startDate, endDate, imageURL) VALUES (#{activityID}, #{merchantID}, #{name}, #{description}, #{startDate}, #{endDate}, #{imageURL})";
    final String deleteByActivityID = "DELETE FROM activity WHERE activityID = #{activityID}";


    @Select(getActivityByActivityID)
    Activity getActivityByActivityID(String activityID);

    @Select(getActivitiesByMerchantID)
    List<Activity> getActivitiesByMerchantID(String merchantID);

    @Update(updateActivity)
    int updateActivity(Activity activity);

    @Insert(insertActivity)
    int insertActivity(Activity activity);

    @Delete(deleteByActivityID)
    int deleteByActivityID(String activityID);

}




