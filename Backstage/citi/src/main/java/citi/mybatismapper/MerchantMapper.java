package citi.mybatismapper;

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

    final String getSome = "SELECT * FROM merchant ORDER BY name LIMIT #{start-1}, #{length}";
    final String getById = "SELECT * FROM merchant WHERE MerchantID = #{Mercantid}";
    final String getTypes = "SELECT * FROM cardtype WHERE MerchantID = #{Mercantid}";

    @Select(getSome)
    List<Merchant> select(int start, int length);

    @Select(getById)
    Merchant selectByID(String MerchantID);

    // 返回商家所有的卡的类型
    @Select(getTypes)
    List<MSCardType> selectTypes(String merchantID);

}
