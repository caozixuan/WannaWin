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

    final String addMerchant = "INSERT INTO merchant(MerchantID, name, password, description, address, logoURL, proportion) " +
            "VALUES(#{merchantID}, #{name}, #{password}, #{description}, #{address}, #{logoURL}, #{proportion})";
    final String loginMerchant = "SELECT * FROM merchant WHERE MerchantID = #{merchant}";
    final String getSome = "SELECT * FROM merchant ORDER BY name LIMIT #{start}, #{length}";
    final String getById = "SELECT * FROM merchant WHERE MerchantID = #{Mercantid}";

    @Insert(addMerchant)
    int addMerchant(Merchant merchantDAO);

    @Select(loginMerchant)
    Merchant loginMerchant(String merchantID);

    //The return sequence will be [start+1, start+2, ,,, start+length].
    @Select(getSome)
    List<Merchant> select(@Param("start") int start, @Param("length") int length);

    @Select(getById)
    Merchant selectByID(String MerchantID);

}
