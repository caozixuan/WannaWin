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

    final String addMerchant = "INSERT INTO merchant(MerchantID, name, password, description, address, logoURL, cardLogoURL, proportion) " +
            "VALUES(#{merchantID}, #{name}, #{password}, #{description}, #{address}, #{logoURL}, #{cardLogoURL}, #{proportion})";
    final String updateMerchantLogo = "UPDATE merchant SET logoURL = #{logoURL} WHERE MerchantID = #{merchantID}";
    final String updateMerchantCardLogo = "UPDATE merchant SET cardLogoURL = #{cardLogoURL} WHERE MerchantID = #{merchantID}";
    final String loginMerchant = "SELECT * FROM merchant WHERE MerchantID = #{merchant} AND password = #{password}";
    final String getSome = "SELECT * FROM merchant ORDER BY name LIMIT #{start}, #{length}";
    final String getById = "SELECT * FROM merchant WHERE MerchantID = #{Mercantid}";

    @Insert(addMerchant)
    int addMerchant(Merchant merchantDAO);

    @Select(loginMerchant)
    Merchant loginMerchant(@Param("merchantID") String merchantID, @Param("password") String password);

    @Update(updateMerchantLogo)
    int updateMerchantLogo(@Param("logoURL") String logoURL, @Param("merchantID") String merchantID);

    @Update(updateMerchantCardLogo)
    int updateMerchantCardLogo(@Param("cardLogoURL") String cardLogoURL, @Param("merchantID") String merchantID);

    //The return sequence will be [start+1, start+2, ,,, start+length].
    @Select(getSome)
    List<Merchant> select(@Param("start") int start, @Param("length") int length);

    @Select(getById)
    Merchant selectByID(String MerchantID);

}
