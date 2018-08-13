package citiMerchant.mapper;

import citiMerchant.vo.Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper {

    final String getItemByItemID = "SELECT * FROM item WHERE ItemID = #{itemID}";
    final String addItem = "INSERT INTO item(ItemID, name, description, MerchantID, logoURL, originalPrice, points, overdueTime, stock) " +
            "VALUES(#{itemID}, #{name}, #{description}, #{merchantID}, #{logoURL}, #{originalPrice}, #{points}, #{overdueTime}, #{stock})";
    final String getItem = "SELECT * FROM item LIMIT #{start}, #{length}";
    final String getItemByMerchantID = "SELECT * FROM item WHERE MerchantID = #{merchantID} LIMIT #{start}, #{length}";
    final String updateItemByID = "UPDATE item SET name = #{name}, description = #{description}, logoURL = #{logoURL}, originalPrice = #{originalPrice}, points = #{points}, overdueTime = #{overdueTime}, stock = #{stock} " +
            "WHERE ItemID = #{itemID}";
    final String updateItemOverdueTimeByID = "UPDATE item SET overdueTime = #{overdueTime} WHERE ItemID = #{itemID}";
    final String updateItemLogoURLByID = "UPDATE item SET logoURL = #{logoURL} WHERE ItemID = #{itemID}";
    final String updateItemStockByID = "UPDATE item SET stock = #{stock} WHERE ItemID = #{itemID}";
    final String deleteItemByID = "DELETE FROM item WHERE ItemID = #{itemID}";

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

    final String getItemAmountByMerchantID = "SELECT COUNT(*) FROM item WHERE merchantID = #{merchantID}";

    @Select(getItemAmountByMerchantID)
    int getItemAmountByMerchantID(String merchantID);

}
