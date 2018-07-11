package citi.mybatismapper;

import citi.vo.CitiCard;
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


    final String addItem = "INSERT INTO item(ItemID, description, MerchantID, discount) " +
            "VALUES(#{ItemID}, #{description}, #{merchantID}, #{discount})";
    final String getItemByMerchantID = "SELECT * FROM item WHERE MerchantID = #{merchantID}";
    final String deleteItemByID = "DELETE FROM item WHERE ItemID = #{ItemID}";

    @Insert(addItem)
    int addItem(Item item);

    @Select(getItemByMerchantID)
    List<Item> getItemByMerchantID(String merchantID);

    @Delete(deleteItemByID)
    int deleteItemByID(String ItemID);

}
