package citi.mybatismapper;

import citi.vo.CitiCard;
import citi.vo.Item;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/*
 * 接口设计：刘钟博
 * 代码填充：任思远
 */

@Repository
public interface ItemMapper {

    final String addItem = "INSERT INTO item(ItemID, description, MerchantID, Points, Type, OriginalPrice, AfterPrice, Discount) " +
            "VALUES(#{ItemID}, #{description}, #{merchantID}, #{points}, #{type}, #{originalPrice}, #{afterPrice}, #{discount})";
    final String updateItem = "";
    final String deleteItem = "DELETE FROM item WHERE ItemID = #{ItemID}";

    @Insert(addItem)
    int addItem(Item item);

//    @Update(updateItem)
//    int updateItem(String nothing);

    @Delete(deleteItem)
    int deleteItem(String ItemID);

}
