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

    final String getSome = "SELECT * FROM merchant ORDER BY name LIMIT #{start}, #{length}";
    final String getById = "SELECT * FROM merchant WHERE MerchantID = #{Mercantid}";
    final String getTypes = "SELECT * FROM cardtype WHERE MerchantID = #{Mercantid}";

    //The return sequence will be [start+1, start+2, ,,, start+length].
    @Select(getSome)
    List<MerchantDAO> select(@Param("start") int start, @Param("length") int length);


    @Select(getById)
    Merchant selectByID(String MerchantID);

    // 返回商家所有的卡的类型
    @Select(getTypes)
    List<MSCardType> selectTypes(String merchantID);

}
