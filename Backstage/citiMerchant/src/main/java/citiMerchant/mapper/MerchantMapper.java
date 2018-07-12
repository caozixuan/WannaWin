package citiMerchant.mapper;

import citiMerchant.vo.Merchant;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantMapper {

    final String addMerchant = "INSERT INTO merchant(MerchantID, name, password, description, address, logoURL, cardLogoURL, proportion) " +
            "VALUES(#{merchantID}, #{name}, #{password}, #{description}, #{address}, #{logoURL}, #{cardLogoURL}, #{proportion})";
    final String loginMerchant = "SELECT * FROM merchant WHERE MerchantID = #{merchantID} AND password = #{password}";
    final String getSome = "SELECT * FROM merchant ORDER BY name LIMIT #{start}, #{length}";
    final String getById = "SELECT * FROM merchant WHERE MerchantID = #{Mercantid}";

    @Insert(addMerchant)
    int addMerchant(Merchant merchantDAO);

    @Select(loginMerchant)
    Merchant loginMerchant(@Param("merchantID") String merchantID, @Param("password") String password);

    //The return sequence will be [start+1, start+2, ,,, start+length].
    @Select(getSome)
    List<Merchant> select(@Param("start") int start, @Param("length") int length);

    @Select(getById)
    Merchant selectByID(String MerchantID);

}
