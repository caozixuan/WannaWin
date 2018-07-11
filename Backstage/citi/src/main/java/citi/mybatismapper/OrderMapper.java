package citi.mybatismapper;

import citi.dao.OrderDAO;
//import citi.dao.OrderStateTypeHandler;
import citi.vo.Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    final String getByOrderID = "SELECT * FROM order WHERE OrderID = #{orderID}";
    final String addOrder = "INSERT INTO order(orderID, originalPrice, priceAfter, pointsNeeded, userID, state, merchantID, time) " +
            "VALUES(#{orderId}, #{originalPrice}, #{priceAfter}, #{pointsNeeded}, #{userId}, #{state}, #{merchantId}, #{time})";
    final String getOrderIDByUserID = "SELECT orderID FROM order WHERE userID = #{userID}";

    @Select(getByOrderID)
    OrderDAO selectOrderByID(String orderID);

    @Insert(addOrder)
    int addOrder(OrderDAO order);

    @Select(getOrderIDByUserID)
    List<String> getOrderIDByUserID(String userID);

}
