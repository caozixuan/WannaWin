package citi.mapper;

import citi.vo.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackMapper {

    final String getFeedbackByID = "SELECT * FROM feedback WHERE userID = #{userID}";
    final String insertFeedback = "INSERT INTO feedback(userID, content, time) " +
            "VALUES(#{userID}, #{content}, #{time})";

    @Select(getFeedbackByID)
    List<Feedback> getFeedbackByID(String userID);

    @Insert(insertFeedback)
    int insert(Feedback feedback);

}
