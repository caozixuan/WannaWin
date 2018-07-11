package citi.mybatismapper;

import citi.dao.MerchantDAO;
import citi.vo.MSCardType;
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

    final String addMerchant = "INSERT INTO merchant(MerchantID, name, password, description, address, logoURL) " +
            "VALUES(#{merchantID}, #{name}, #{password}, #{description}, #{address}, #{logoURL})";
    final String loginMerchant = "SELECT * FROM merchant WHERE MerchantID = #{merchant}";
    final String getSome = "SELECT MerchantID, name, description, address, logoURL FROM merchant ORDER BY name LIMIT #{start}, #{length}";
    final String getById = "SELECT MerchantID, name, description, address, logoURL FROM merchant WHERE MerchantID = #{Mercantid}";
    final String getTypes = "SELECT * FROM cardtype WHERE MerchantID = #{Merchantid}";


    @Insert(addMerchant)
    int addMerchant(MerchantDAO merchantDAO);

    @Select(loginMerchant)
    MerchantDAO loginMerchant(String merchantID);

    //The return sequence will be [start+1, start+2, ,,, start+length].
    @Select(getSome)
    List<Merchant> select(@Param("start") int start, @Param("length") int length);


    @Select(getById)
    Merchant selectByID(String MerchantID);

    // 返回商家所有的卡的类型
    @Select(getTypes)
    List<MSCardType> selectTypes(String merchantID);

}
