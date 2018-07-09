package citi.mybatismapper;

import citi.vo.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    final String getByID = "SELECT * FROM order WHERE OrderID = #{orderID}";

    @Select(getByID)
    @Results(
            value = {
                    @Result(property = "orderId", column = "orderId"),
                    @Result(property = "originalPrice", column = "originalPrice"),
                    @Result(property = "priceAfter", column = "priceAfter"),
                    @Result(property = "pointsNeeded", column = "pointsNeeded"),
                    @Result(property = "userId", column = "userId"),
                    @Result(property = "state", column = "state"),
                    @Result(property = "merchantId", column = "merchantId")
            }
    )
    Order select(String orderID);

}
