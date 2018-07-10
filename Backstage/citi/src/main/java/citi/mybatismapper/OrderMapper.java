package citi.mybatismapper;

import citi.dao.OrderDAO;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    final String getByID = "SELECT * FROM order WHERE OrderID = #{orderID}";
    final String addOrder = "INSERT INTO order(orderID, originalPrice, priceAfter, pointsNeeded, userID, state, merchantID) " +
            "VALUES(#{orderId}, #{originalPrice}, #{priceAfter}, #{pointsNeeded}, #{userId}, #{state}, #{merchantId})";

    @Select(getByID)
    OrderDAO selectOrderByID(String orderID);

    @Insert(addOrder)
    int addOrder(OrderDAO order);

}
