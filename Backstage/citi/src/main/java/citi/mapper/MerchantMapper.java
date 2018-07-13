package citi.mapper;

import citi.vo.Merchant;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface MerchantMapper {

    final String addMerchant = "INSERT INTO merchant(MerchantID, name, password, description, cardDescription, address, merchantLogoURL, cardLogoURL, proportion, activityTheme, activityDescription) " +
            "VALUES(#{merchantID}, #{name}, #{password}, #{description}, #{cardDescription}, #{address}, #{merchantLogoURL}, #{cardLogoURL}, #{proportion}, #{activityTheme}, #{activityDescription})";
    final String updateMerchantLogo = "UPDATE merchant SET merchantLogoURL = #{merchantLogoURL} WHERE MerchantID = #{merchantID}";
    final String updateMerchantCardLogo = "UPDATE merchant SET cardLogoURL = #{cardLogoURL} WHERE MerchantID = #{merchantID}";
    final String updateActivityTheme = "UPDATE merchant SET activityTheme = #{activityTheme} WHERE MerchantID = #{merchnatID}";
    final String updateActivityDescription = "\"UPDATE merchant SET activityDescription = #{activityDescription} WHERE MerchantID = #{merchnatID}";
    final String loginMerchant = "SELECT * FROM merchant WHERE MerchantID = #{merchantID} AND password = #{password}";
    final String getSome = "SELECT * FROM merchant ORDER BY name LIMIT #{start}, #{length}";
    final String getById = "SELECT * FROM merchant WHERE MerchantID = #{Mercantid}";
    final String changePassword = "UPDATE merchant SET password = #{password} WHERE MerchantID = #{merchantID}";

    @Insert(addMerchant)
    int addMerchant(Merchant merchantDAO);

    @Select(loginMerchant)
    Merchant loginMerchant(@Param("merchantID") String merchantID, @Param("password") String password);

    @Update(updateMerchantLogo)
    int updateMerchantLogo(@Param("merchantLogoURL") String merchantLogoURL, @Param("merchantID") String merchantID);

    @Update(updateMerchantCardLogo)
    int updateMerchantCardLogo(@Param("cardLogoURL") String cardLogoURL, @Param("merchantID") String merchantID);

    @Update(updateActivityTheme)
    int updateActivityTheme(@Param("merchantID") String merchantID, @Param("activityTheme") String activityTheme);

    @Update(updateActivityDescription)
    int updateActivityDescription(@Param("merchantID") String merchantID, @Param("activityDescription") String activityDescription);

    //The return sequence will be [start+1, start+2, ,,, start+length].
    @Select(getSome)
    List<Merchant> select(@Param("start") int start, @Param("length") int length);

    @Select(getById)
    Merchant selectByID(String MerchantID);

    @Update(changePassword)
    int changePassword(@Param("merchantID") String merchantID, @Param("password") String password);

}
