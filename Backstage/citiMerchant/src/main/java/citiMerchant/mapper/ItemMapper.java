package citiMerchant.mapper;

import citiMerchant.vo.Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface ItemMapper {

    final String getItemByItemID = "SELECT * FROM item WHERE ItemID = #{ItemID}";
    final String addItem = "INSERT INTO item(ItemID, name, description, MerchantID, logoURL, originalPrice, points, overdueTime, stock) " +
            "VALUES(#{ItemID}, #{name}, #{description}, #{merchantID}, #{logoURL}, #{originalPrice}, #{overdueTime} #{stock})";
    final String getItemByMerchantID = "SELECT * FROM item WHERE MerchantID = #{merchantID}";
    final String updateItemByID = "UPDATE item SET name = #{name}, description = #{description}, logoURL = #{logoURL}, originalPrice = #{originalPrice}, points = #{points}, overdueTime = #{overdueTime}, stock = #{stock}" +
            "WHERE ItemID = #{ItemID}";
    final String updateItemStockByID = "UPDATE item SET stock = #{stock} WHERE ItemID = #{ItemID}";
    final String deleteItemByID = "DELETE FROM item WHERE ItemID = #{ItemID}";

    @Select(getItemByItemID)
    Item getItemByItemID(String ItemID);

    @Insert(addItem)
    int addItem(Item item);

    @Select(getItemByMerchantID)
    List<Item> getItemByMerchantID(String merchantID);

    @Update(updateItemByID)
    int updateItemByID(Item item);

    @Update(updateItemStockByID)
    int updateItemStockByID(@Param("ItemID") String ItemID, @Param("stock") long stock);

    @Delete(deleteItemByID)
    int deleteItemByID(String ItemID);

}
