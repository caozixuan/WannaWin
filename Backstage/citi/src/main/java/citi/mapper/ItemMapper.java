package citi.mapper;

import citi.vo.Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {

    final String getItemByItemID = "SELECT * FROM item WHERE ItemID = #{ItemID}";
    final String addItem = "INSERT INTO item(ItemID, name, description, MerchantID, logoURL, originalPrice, points, overdueTime, stock) " +
            "VALUES(#{ItemID}, #{name}, #{description}, #{merchantID}, #{logoURL}, #{originalPrice}, #{overdueTime} #{stock})";
    final String getItem = "SELECT * FROM item LIMIT #{start}, #{length}";
    final String getItemByMerchantID = "SELECT * FROM item WHERE MerchantID = #{merchantID} LIMIT #{start}, #{length}";
    final String updateItemByID = "UPDATE item SET name = #{name}, description = #{description}, logoURL = #{logoURL}, originalPrice = #{originalPrice}, points = #{points}, overdueTime = #{overdueTime}, stock = #{stock} " +
            "WHERE ItemID = #{ItemID}";
    final String updateItemOverdueTimeByID = "UPDATE item SET overdueTime = #{overdueTime} WHERE ItemID = #{ItemID}";
    final String updateItemLogoURLByID = "UPDATE item SET logoURL = #{logoURL} WHERE ItemID = #{ItemID}";
    final String updateItemStockByID = "UPDATE item SET stock = #{stock} WHERE ItemID = #{ItemID}";
    final String deleteItemByID = "DELETE FROM item WHERE ItemID = #{ItemID}";

    @Select(getItemByItemID)
    Item getItemByItemID(String ItemID);

    @Insert(addItem)
    int addItem(Item item);

    @Select(getItem)
    List<Item> getItem(@Param("start") int start, @Param("length") int length);

    @Select(getItemByMerchantID)
    List<Item> getItemByMerchantID(@Param("merchantID") String merchantID, @Param("start") int start, @Param("length") int length);

    @Update(updateItemByID)
    int updateItemByID(Item item);

    @Update(updateItemOverdueTimeByID)
    int updateItemOverdueTimeByID(@Param("overdueTime") String overdueTime, @Param("ItemID") String ItemID);

    @Update(updateItemLogoURLByID)
    int updateItemLogoURLByID(@Param("logoURL") String logoURL, @Param("ItemID") String ItemID);

    @Update(updateItemStockByID)
    int updateItemStockByID(@Param("ItemID") String ItemID, @Param("stock") long stock);

    @Delete(deleteItemByID)
    int deleteItemByID(String ItemID);

    final String getItemAmountByMerchantID = "SELECT COUNT(*) FROM item WHERE MerchantID = #{merchantID}";

    @Select(getItemByMerchantID)
    int getItemAmountByMerchantID(String merchantID);

}
