package citi.mapper;

import citi.vo.Item;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface ItemMapper {


    final String addItem = "INSERT INTO item(ItemID, name, description, MerchantID, logoURL, originalPrice, points, overdueTime) " +
            "VALUES(#{ItemID}, #{name}, #{description}, #{merchantID}, #{logoURL}, #{originalPrice}, #{overdueTime})";
    final String getItemByMerchantID = "SELECT * FROM item WHERE MerchantID = #{merchantID}";
    final String updateItemByID = "UPDATE item SET name = #{name}, description = #{description}, logoURL = #{logoURL}, originalPrice = #{originalPrice}, points = #{points}, overdueTime = #{overdueTime}" +
            "WHERE ItemID = #{ItemID}";
    final String deleteItemByID = "DELETE FROM item WHERE ItemID = #{ItemID}";

    @Insert(addItem)
    int addItem(Item item);

    @Select(getItemByMerchantID)
    List<Item> getItemByMerchantID(String merchantID);

    @Update(updateItemByID)
    int updateItemByID(Item item);

    @Delete(deleteItemByID)
    int deleteItemByID(String ItemID);

}
