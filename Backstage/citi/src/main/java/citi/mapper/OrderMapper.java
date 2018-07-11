package citi.mapper;

import citi.vo.Order;
//import citi.dao.OrderStateTypeHandler;
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
    Order selectOrderByID(String orderID);

    @Insert(addOrder)
    int addOrder(Order order);

    @Select(getOrderIDByUserID)
    List<String> getOrderIDByUserID(String userID);

}
