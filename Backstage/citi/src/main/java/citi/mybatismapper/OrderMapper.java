package citi.mybatismapper;

import citi.vo.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    final String getByID = "SELECT * FROM order WHERE OrderID = #{orderID}";

    @Select(getByID)
    Order select(String orderID);

}
