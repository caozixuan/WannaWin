package citiMerchant.mapper;

import citiMerchant.vo.Merchant;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantMapper {

    final String addMerchant = "INSERT INTO merchant(MerchantID, name, password, description, cardDescription, address, logoURL, cardLogoURL, proportion) " +
            "VALUES(#{merchantID}, #{name}, #{password}, #{description}, #{cardDescription}, #{address}, #{logoURL}, #{cardLogoURL}, #{proportion})";
    final String updateMerchantLogo="UPDATE merchant SET logoURL = #{logoURL} WHERE MerchantID = #{merchantID}";
    final String updateMerchantCardLogo="UPDATE merchant SET cardLogoURL = #{cardLogoURL} WHERE MerchantID = #{merchantID}";
    final String loginMerchant = "SELECT * FROM merchant WHERE MerchantID = #{merchant} AND password = #{password}";
    final String getSome = "SELECT * FROM merchant ORDER BY name LIMIT #{start}, #{length}";
    final String getById = "SELECT * FROM merchant WHERE MerchantID = #{Mercantid}";

    @Insert(addMerchant)
    int addMerchant(Merchant merchantDAO);

    @Update(updateMerchantLogo)
    int updateMerchantLogo(@Param("logoURL") String logoURL, @Param("merchantID") String merchantID);

    @Update(updateMerchantCardLogo)
    int updateMerchantCardLogo(@Param("cardLogoURL") String cardLogoURL, @Param("merchantID") String merchantID);

    @Select(loginMerchant)
    Merchant loginMerchant(@Param("merchantID") String merchantID, @Param("password") String password);

    //The return sequence will be [start+1, start+2, ,,, start+length].
    @Select(getSome)
    List<Merchant> select(@Param("start") int start, @Param("length") int length);

    @Select(getById)
    Merchant selectByID(String MerchantID);

}
