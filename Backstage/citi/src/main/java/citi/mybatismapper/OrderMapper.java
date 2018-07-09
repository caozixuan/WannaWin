package citi.mybatismapper;

import citi.dao.OrderDAO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    final String getByID = "SELECT * FROM order WHERE OrderID = #{orderID}";

    @Select(getByID)
    OrderDAO select(String orderID);

}
