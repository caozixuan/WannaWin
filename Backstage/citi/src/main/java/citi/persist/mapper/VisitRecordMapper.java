package citi.persist.mapper;


import citi.vo.VisitRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRecordMapper {

    final String getItemIDByUserID = "SELECT itemID FROM visitRecord WHERE userID = #{userID} AND time >= now() - #{intervalTime}";
    final String getVisitTimes = "SELECT COUNT(*) FROM visitRecord WHERE userID = #{userID} AND itemID = #{itemID}";
    final String insertVisitRecord = "INSERT INTO visitRecord (userID, itemID, time) VALUES (#{userID}, #{itemID}, #{time})";
    final String deleteByUserID = "DELETE FROM visitRecord WHERE userID = #{userID} AND time <= now() - #{intervalTime}";


    @Select(getItemIDByUserID)
    List<String> getItemIDByUserID(@Param("userID") String userID, @Param("intervalTime") String intervalTime);

    @Select(getVisitTimes)
    Integer getVisitTimes(@Param("userID") String userID, @Param("itemID") String itemID);

    @Insert(insertVisitRecord)
    int insertVisitRecord(VisitRecord visitRecord);

    //从数据库中删除用户很久以前的访问记录
    @Delete(deleteByUserID)
    int deleteByUserID(@Param("userID") String userID, @Param("intervalTime") String intervalTime);

}




