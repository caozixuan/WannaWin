package citi.dao;

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

    final String getSome = "SELECT * FROM merchant ORDER BY MerchantID LIMIT #{start-1}, #{length}";
    final String getById = "SELECT * FROM merchant WHERE MerchantID = #{Mercantid}";
    final String getTypes = "SELECT * FROM cardtype WHERE MerchantID = #{Mercantid}";

    @Select(getSome)
    @Results(
            value = {
                    @Result(property = "merchantID", column = "merchantID"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "description", column = "description"),
                    @Result(property = "address", column = "address"),
                    @Result(property = "logoURL", column = "logoURL")
            }
    )
    List<Merchant> select(int start, int length);

    @Select(getById)
    @Results(
            value = {
                    @Result(property = "merchantID", column = "merchantID"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "description", column = "description"),
                    @Result(property = "address", column = "address"),
                    @Result(property = "logoURL", column = "logoURL")
            }
    )
    Merchant select(String MerchantID);

    // 曹子轩：返回商户对应的卡类型，这个属于数据库的操作吧？
    // 返回商家所有有的卡的类型
    @Select(getTypes)
    @Results(
            value = {
                    @Result(property = "MerchantID", column = "MerchantID"),
                    @Result(property = "MType", column = "MType"),
                    @Result(property = "CardType", column = "CardType"),
                    @Result(property = "Proportion", column = "Proportion")
            }
    )
    List<MSCardType> selectTypes(String merchantID);
}
